package com.volmit.react.util;

/**
 * Represents a storage engine for playerdata
 *
 * @author cyberpwn
 *
 * @param <K>
 *            the key type (usually uuid or string)
 * @param <V>
 *            the value type (the data object)
 */
public interface IStorageEngine<K, V>
{
	/**
	 * Load a data object
	 *
	 * @param key
	 *            the key
	 * @return the object
	 */
	public V load(K key);

	/**
	 * Save the data object
	 *
	 * @param key
	 *            the key
	 * @param val
	 *            the object
	 */
	public void save(K key, V val);

	/**
	 * Check if a key has data on it
	 *
	 * @param key
	 *            the key
	 * @return true if it can be loaded
	 */
	public boolean exists(K key);

	/**
	 * Delete data by key
	 *
	 * @param key
	 *            the key
	 */
	public void delete(K key);
}
