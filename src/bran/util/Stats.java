package bran.util;

import java.util.Collection;
import java.util.Map;

public class Stats {

	public static double stdDev(Collection<Double> data) {
		double sum = 0d;
		double mean = mean(data);
		for (Double d : data)
			sum += Math.pow(d - mean, 2);
		return Math.sqrt(sum / data.size());
	}

	public static double mean(Collection<Double> data) {
		double sum = 0d;
		for (Double d : data)
			sum += d;
		return sum / data.size();
	}

	public static Map.Entry<Double, Double> stdMeanPair(Collection<Double> data) {
		double sum = 0d;
		double mean = mean(data);
		for (Double d : data)
			sum += Math.pow(d - mean, 2);
		return new Pair<Double, Double>(mean, Math.sqrt(sum / data.size()));

	}

	public static class Pair<K, V> implements Map.Entry<K, V>{

		private K key;
		private V value;

		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			return this.value = value;
		}

	}

}
