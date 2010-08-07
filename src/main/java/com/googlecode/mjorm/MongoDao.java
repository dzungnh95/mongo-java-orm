package com.googlecode.mjorm;

import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * An interface for working with mapped objects in mongo.
 */
public interface MongoDao {

	/**
	 * Creates the object in the given collection.
	 * @param <T> the type
	 * @param collection the collection
	 * @param object the object to create
	 * @return the created object
	 */
	<T> T createObject(String collection, T object);

	/**
	 * Creates the given objects in the given collection.
	 * @param <T> the type
	 * @param collection the collection
	 * @param objects the objects to create
	 * @return the created objects
	 */
	<T> T[] createObjects(String collection, T[] objects);

	/**
	 * Maps and returns an object from the given collection.
	 * @param <T> the type
	 * @param collection the collection
	 * @param id the object's id
	 * @param clazz the object's class
	 * @return the object read
	 */
	<T> T readObject(String collection, String id, Class<T> clazz);

	/**
	 * Maps and returns a objects from the given collection.
	 * @param <T> the type
	 * @param collection the collection
	 * @param ids the object's id
	 * @param clazz the object's class
	 * @return the objects read
	 */
	<T> T[] readObjects(String collection, String[] ids, Class<T> clazz);

	/**
	 * Updates the object with the given id in the given
	 * collection.
	 * @param collection the collection
	 * @param id the id
	 * @param o the object
	 */
	void updateObject(String collection, String id, Object o);

	/**
	 * Deletes the object with the given id from the
	 * given collection.
	 * @param collection the collection
	 * @param id the id
	 */
	void deleteObject(String collection, String id);

	/**
	 * Deletes the objects matching the given query from
	 * the given collection.
	 * @param collection the collection
	 * @param query the query
	 */
	void deleteObjects(String collection, DBObject query);

	/**
	 * Deletes the objects matching the given query from
	 * the given collection.
	 * @param collection the collection
	 * @param ids the ids of the objects to delete
	 */
	void deleteObjects(String collection, String[] ids);

	/**
	 * Finds objects by the given example in the given
	 * collection and returns an {@link ObjectIterator}
	 * for them.
	 * @param <T> the object type
	 * @param collection the collection
	 * @param example the example
	 * @param clazz the class
	 * @return the {@link ObjectIterator}
	 */
	<T> ObjectIterator<T> findByExample(String collection, T example, Class<T> clazz);

	/**
	 * Maps and returns an {@link ObjectIterator} for objects
	 * matching the given query in the given collection.
	 * @param <T> the type
	 * @param collection the collection
	 * @param query the query
	 * @param clazz the java type to map the objects to
	 * @return the {@link ObjectIterator}
	 */
	<T> ObjectIterator<T> findObjects(String collection, DBObject query, Class<T> clazz);

	/**
	 * Maps and returns an {@link ObjectIterator} for objects
	 * matching the given query in the given collection.
	 * @param <T> the type
	 * @param collection the collection
	 * @param query the query
	 * @param startIndex the first object to return
	 * @param numObjects the number of objects to return
	 * @param clazz the java type to map the objects to
	 * @return the {@link ObjectIterator}
	 */
	<T> ObjectIterator<T> findObjects(String collection, DBObject query,
		int startIndex, int numObjects, Class<T> clazz);

	/**
	 * Maps and returns an {@link ObjectIterator} for objects
	 * matching the given query in the given collection.
	 * @param <T> the type
	 * @param collection the collection
	 * @param query the query
	 * @param startIndex the first object to return
	 * @param numObjects the number of objects to return
	 * @param batchSize the batchSize
	 * @param clazz the java type to map the objects to
	 * @return the {@link ObjectIterator}
	 */
	<T> ObjectIterator<T> findObjects(String collection, DBObject query,
		int startIndex, int numObjects, int batchSize, Class<T> clazz);

	/**
	 * Returns the count of objects matching the given query
	 * in the given collection.
	 * @param collection the collection
	 * @param query the query
	 * @return the count
	 */
	long countObjects(String collection, DBObject query);

	/**
	 * Maps and returns a single object matching the given query
	 * from the given collection.
	 * @param <T> the type
	 * @param collection the collection
	 * @param query the query
	 * @param clazz the java type to map the objects to
	 * @return the object, or null if not found
	 */
	<T> T findObject(String collection, DBObject query, Class<T> clazz);

	/**
	 * Executes the given command.
	 * @param cmd the command to execute
	 * @return the result
	 */
	CommandResult executeCommand(DBObject cmd);

	/**
	 * Executes the given command.
	 * @param cmd the command to execute
	 * @return the result
	 */
	CommandResult executeCommand(String cmd);

	/**
	 * Executes a MapReduce job using the given {@link MapReduceConfiguration}.
	 * @param collection the collection to run in on
	 * @param mapReduce the {@link MapReduce}
	 * @return the {@link MapReduceOutput}
	 */
	MapReduceResult mapReduce(String collection, MapReduce mapReduce);

	/**
	 * Ensures that an index exists on the given collection.
	 * @param collection the collection
	 * @param key the key
	 * @param background whether or not to build the index in the background
	 * @param unique if it's a unique index
	 * @param dropDupes whether or not to drop duplicate documents
	 */
	void ensureIndex(String collection, String key,
		boolean background, boolean unique, boolean dropDupes);

	/**
	 * Ensures that an index exists on the given collection.
	 * @param collection the collection
	 * @param keys the keys
	 * @param background whether or not to build the index in the background
	 * @param unique if it's a unique index
	 * @param dropDupes whether or not to drop duplicate documents
	 */
	void ensureIndex(String collection, DBObject keys,
		boolean background, boolean unique, boolean dropDupes);

	/**
	 * Returns the underlying {@link DB}.
	 * @return the {@link DB}
	 */
	DB getDB();

	/**
	 * Returns a {@link DBCollection} by it's name.
	 * @param name the name
	 * @return the {@link DBCollection}
	 */
	DBCollection getCollection(String name);
}
