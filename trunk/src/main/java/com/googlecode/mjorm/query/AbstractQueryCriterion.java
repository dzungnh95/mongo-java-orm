package com.googlecode.mjorm.query;

import java.util.Collection;
import java.util.regex.Pattern;

import com.googlecode.mjorm.query.TypeCriterion.Type;

/**
 * An object for building MongoDB queries using
 * {@link Criterion}.
 * 
 * @see Criterion
 * @see Criteria
 */
public abstract class AbstractQueryCriterion<T extends AbstractQueryCriterion<T>>
	extends AbstractCriterionBuilder<T> {

	/**
	 * Adds a query the {@link QueryGroup} for
	 * {@code $or}.
	 * @param queryCriterion the {@link AbstractQueryCriterion} to add
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T or(Query queryCriterion) {
		group("$or", queryCriterion);
		return self();
	}

	/**
	 * Adds a query the {@link QueryGroup} for
	 * {@code $nor}.
	 * @param queryCriterion the {@link AbstractQueryCriterion} to add
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T nor(Query queryCriterion) {
		group("$nor", queryCriterion);
		return self();
	}

	/**
	 * Adds a query the {@link QueryGroup} for
	 * {@code $and}.
	 * @param queryCriterion the {@link AbstractQueryCriterion} to add
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T and(Query queryCriterion) {
		group("$and", queryCriterion);
		return self();
	}

	/**
	 * {@see Criteria#eq(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T eq(String property, Object value) {
		return add(property, Criteria.eq(value));
	}

	/**
	 * {@see Criteria#gt(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T gt(String property, Object value) {
		return add(property, Criteria.gt(value));
	}

	/**
	 * {@see Criteria#gte(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T gte(String property, Object value) {
		return add(property, Criteria.gte(value));
	}

	/**
	 * {@see Criteria#lt(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T lt(String property, Object value) {
		return add(property, Criteria.lt(value));
	}

	/**
	 * {@see Criteria#lte(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T lte(String property, Object value) {
		return add(property, Criteria.lte(value));
	}

	/**
	 * {@see Criteria#between(Object, Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T between(String property, Object left, Object right) {
		return add(property, Criteria.between(left, right));
	}

	/**
	 * {@see Criteria#ne(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T ne(String property, Object value) {
		return add(property, Criteria.ne(value));
	}

	/**
	 * {@see Criteria#in(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T in(String property, Object values) {
		return add(property, Criteria.in(values));
	}

	/**
	 * {@see Criteria#in(T[])}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public <V> T in(String property, V... values) {
		return add(property, Criteria.in(values));
	}

	/**
	 * {@see Criteria#in(Collection)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T in(String property, Collection<?> values) {
		return add(property, Criteria.in(values));
	}

	/**
	 * {@see Criteria#nin(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T nin(String property, Object values) {
		return add(property, Criteria.nin(values));
	}

	/**
	 * {@see Criteria#nin(T[])}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T nin(String property, Object... values) {
		return add(property, Criteria.nin(values));
	}

	/**
	 * {@see Criteria#nin(Collection)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T nin(String property, Collection<?> values) {
		return add(property, Criteria.nin(values));
	}

	/**
	 * {@see Criteria#all(Object)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T all(String property, Object values) {
		return add(property, Criteria.all(values));
	}

	/**
	 * {@see Criteria#all(T[])}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T all(String property, Object... values) {
		return add(property, Criteria.all(values));
	}

	/**
	 * {@see Criteria#all(Collection)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T all(String property, Collection<?> values) {
		return add(property, Criteria.all(values));
	}

	/**
	 * {@see Criteria#exists(Boolean)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T exists(String property, Boolean value) {
		return add(property, Criteria.exists(value));
	}

	/**
	 * {@see Criteria#mod(Number, Number)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T mod(String property, Number left, Number right) {
		return add(property, Criteria.mod(left, right));
	}

	/**
	 * {@see Criteria#regex(Pattern)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T regex(String property, Pattern pattern) {
		return add(property, Criteria.regex(pattern));
	}

	/**
	 * {@see Criteria#regex(String)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T regex(String property, String pattern) {
		return add(property, Criteria.regex(pattern));
	}

	/**
	 * {@see Criteria#regex(String, int)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T regex(String property, String pattern, int flags) {
		return add(property, Criteria.regex(pattern, flags));
	}

	/**
	 * {@see Criteria#size(Number)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T size(String property, Number size) {
		return add(property, Criteria.size(size));
	}

	/**
	 * {@see Criteria#type(Number)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T type(String property, Number typeCode) {
		return add(property, Criteria.type(typeCode));
	}

	/**
	 * {@see Criteria#type(Type)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T type(String property, Type type) {
		return add(property, Criteria.type(type));
	}

	/**
	 * {@see Criteria#elemMatch(AbstractQueryCriterion)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T elemMatch(String property, Query queryCriterion) {
		return add(property, Criteria.elemMatch(queryCriterion));
	}

	/**
	 * {@see Criteria#not(Criterion)}
	 * @return the {@link AbstractQueryCriterion} for chaining
	 */
	public T not(String property, Criterion criteria) {
		return add(property, Criteria.not(criteria));
	}
}
