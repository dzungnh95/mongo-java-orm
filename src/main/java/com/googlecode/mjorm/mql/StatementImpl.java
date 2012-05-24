package com.googlecode.mjorm.mql;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

import com.googlecode.mjorm.ObjectIterator;
import com.googlecode.mjorm.ObjectMapper;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class StatementImpl
	implements Statement {

	private static final Logger LOGGER = Logger.getLogger(StatementImpl.class.getName());

	private Tree tree;
	private Map<String, Object> parameters;
	private Interpreter interpreter;
	private ObjectMapper objectMapper;

	/**
	 * Creates the {@link StatementImpl}
	 * @param mql {@link InputStream} that contains MQL code
	 * @param db the mongo {@link DB}
	 * @param objectMapper the {@link ObjectMapper}
	 * @throws IOException on error
	 * @throws RecognitionException on error
	 */
	public StatementImpl(InputStream mql, DB db, ObjectMapper objectMapper)
		throws IOException, RecognitionException {
		try {
			this.interpreter = new InterpreterImpl(db, objectMapper);
			this.tree = interpreter.compile(mql);
		} catch(Exception e) {
			throw new MqlException(e);
		}
		this.parameters = new HashMap<String, Object>();
		this.objectMapper = objectMapper;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setParameters(Map<String, Object> params) {
		parameters.clear();
		parameters.putAll(params);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setParameters(Object... params) {
		parameters.clear();
		for (int i=0; i<params.length; i++) {
			parameters.put(i+"", params[i]);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setParameter(String name, Object param) {
		parameters.put(name, param);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setParameter(int index, Object param) {
		parameters.put(index+"", param);
	}

	/**
	 * {@inheritDoc}
	 */
	public void clearParameters() {
		parameters.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	public DBCursor execute() {
		InterpreterResult res = executeInternal(true, false, false);
		return res.getCursor();
	}

	/**
	 * {@inheritDoc}
	 */
	public DBObject executeSingle() {
		InterpreterResult res = executeInternal(false, true, false);
		return res.getObject();
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> ObjectIterator<T> execute(Class<T> clazz) {
		return new ObjectIterator<T>(execute(), objectMapper, clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> T executeSingle(Class<T> clazz) {
		return objectMapper.mapFromDBObject(executeSingle(), clazz);
	}

	/**
	 * {@inheritDoc}
	 */
	public void executeUpdate() {
		executeInternal(false, false, true);
	}


	/**
	 * Executes the statement and returns the very
	 * last {@link InterpreterResult}.
	 * @return
	 */
	private InterpreterResult executeInternal(
		boolean expectCursor, boolean expectObject, boolean expectWriteResult) {
		List<InterpreterResult> res = interpreter.interpret(tree, parameters);
		if (res.isEmpty()) {
			throw new MqlException("No InterpreterResult was returned");
		} else if (res.size()>1) {
			LOGGER.warning(
				"interpretation returned more than one "
				+"InterpreterResult, using the last");
		}
		InterpreterResult ret = res.get(res.size()-1);
		if (!expectCursor && ret.getCursor()!=null) {
			LOGGER.warning("Wasn't expecting a cursor");
		}
		if (!expectObject && ret.getObject()!=null) {
			LOGGER.warning("Wasn't expecting an object");
		}
		if (!expectWriteResult && ret.getResult()!=null) {
			LOGGER.warning("Wasn't expecting a WriteResult");
		}
		
		if (expectCursor && ret.getCursor()==null) {
			throw new MqlException("Expected a cursor, didn't get one");
		}
		if (expectObject && ret.getObject()==null) {
			throw new MqlException("Expected an object, didn't get one");
		}
		if (expectWriteResult && ret.getResult()==null) {
			throw new MqlException("Expected a WriteResult, didn't get one");
		}
		return ret;
	}

}
