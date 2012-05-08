package com.googlecode.mjorm.query.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.googlecode.mjorm.DBObjectUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public abstract class AbstractCriterionBuilder<T extends AbstractCriterionBuilder<T>>
	extends AbstractDBObjectCriterion {

	protected Stack<String> propertyStack 					= new Stack<String>();
	protected List<AbstractDBObjectCriterion> criteria 	= new ArrayList<AbstractDBObjectCriterion>();

	protected abstract T self();

	/**
	 * Clears the query.
	 */
	public void clear() {
		criteria.clear();
		propertyStack.clear();
	}

	/**
	 * Pushed a property onto the property stack.
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T push(String property) {
		propertyStack.push(property);
		return self();
	}

	/**
	 * Pops a property off of the property stack.
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T pop() {
		propertyStack.pop();
		return self();
	}

	/**
	 * Adds a {@link FieldCriterion} to the query using
	 * the given property and criterion.
	 * @param property the property name
	 * @param criterion the {@link Criterion}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T add(String property, Criterion criterion) {
		FieldCriterion fc = new FieldCriterion(property, criterion);
		for (int i=propertyStack.size()-1; i>=0; i--) {
			fc = new FieldCriterion(propertyStack.get(i), fc);
		}
		add(fc);
		return self();
	}

	/**
	 * Adds a {@link Criterion} to the query.
	 * @param criterion the {@link Criterion}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T add(DocumentCriterion criterion) {
		criteria.add(criterion);
		return self();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DBObject toQueryObject() {

		// the query we're returning
		BasicDBObject ret = new BasicDBObject();

		// loop through each criteria
		for (AbstractDBObjectCriterion criterion : criteria) {

			// merge the query
			DBObjectUtil.merge(criterion.toQueryObject(), ret);
		}

		// return it
		return ret;
	}

}