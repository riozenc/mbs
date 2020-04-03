/**
 * Author : chizf
 * Date : 2020年3月6日 下午3:49:40
 * Title : org.mbs.client.TestFinally.java
 *
**/
package org.mbs.client;

public class TestFinally {
	public static void main(String[] args) {
		int t = 1;
		
		System.out.println(get(t));
	}

	public static int get(int i) {
		try {
			i++;
			return i++;
		} finally {
			System.out.println(i+"..");
			return i++;
		}
	}
}
