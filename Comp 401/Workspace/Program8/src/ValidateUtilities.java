

import java.util.*;

/**
 * <P>
 * Utility methods for common argument validations. Throws an exception if
 * conditions are violated.
 * <P>
 * checkForNull(Object aObject)
 * <P>
 * checkForContent(String aText)
 * <P>
 * checkForRange(int aNumber, int aLow, int aHigh) 
 * <P>
 * checkForEmtpyCollection(Collection aCollection)
 * <P>
 * checkForLegalIndex(int index, List<?> list)
 * <P>
 * checkForLegalIndex(int index, Object[] arr );
 * <P>
 * checkForLegalAddPosition(int index, List<?> list);
 * <P>
 * Replace <code>if</code> statements at the start of a method with more
 * compact method calls.
 * 
 * @author <a href="http://www.javapractices.com/">javapractices.com</a>
 */

public final class ValidateUtilities {
	private ValidateUtilities() {
	}
	


	/**
	 * If <code>aObject</code> is null, throw a
	 * <code>NullPointerException</code>.
	 * 
	 * <P>
	 * Use cases :
	 * 
	 * doSomething( SoccerBall aBall ){ //call some method on the argument :
	 * //if aBall is null, then exception is automatically thrown, so //there is
	 * no need for an explicit check for null. aBall.inflate();
	 * 
	 * //assign to a corresponding field (common in constructors): //if aBall is
	 * null, no exception is immediately thrown, so //an explicit check for null
	 * may be useful here Args.checkForNull( aBall ); fBall = aBall;
	 * 
	 * //passed on to some other method as param : //it may or may not be
	 * appropriate to have an explicit check //for null here, according the
	 * needs of the problem Args.checkForNull( aBall ); //?? fReferee.verify(
	 * aBall ); }
	 * 
	 */

	public static void checkForNull(Object aObject) {
		if (aObject == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Throw an <code>IllegalArgumentException</code> if <code>aText</code>
	 * does not satisfy {@link Util#textHasContent}.
	 * 
	 * <P>
	 * Most text used in an application is meaningful only if it has visible
	 * content.
	 */
	public static void checkForContent(String aText) {
		if (!textHasContent(aText)) {
			throw new IllegalArgumentException("Text has no visible content");
		}
	}

	/**
	 * Throw an <code>IllegalArgumentException</code> if
	 * {@link Util#isInRange} returns <code>false</code>.
	 * 
	 * @param aLow
	 *            is less than or equal to <code>aHigh</code>.
	 */
	public static void checkForRange(int aNumber, int aLow, int aHigh) {
		if (!isInRange(aNumber, aLow, aHigh)) {
			throw new IllegalArgumentException(aNumber + " not in range "
					+ aLow + ".." + aHigh);
		}
	}

	/**
	 * Throw an <code>IllegalArgumentException</code> only if
	 * <code>aCollection.isEmpty</code> returns <code>true</code>.
	 */
	public static void checkForEmpty(Collection aCollection) {
		if (aCollection.isEmpty()) {
			throw new IllegalArgumentException("Collection is empty.");
		}
	}

	/**
	 * 
	 * @param index
	 * @param list
	 * @returns true iff index is a legal index into the list
	 */
	public static void checkForLegalIndex(int index, List<?> list) {
		if (!isInRange(index, 0, list.size() - 1))
			throw new IllegalArgumentException("index out of legal range");
	}
	/**
	 * 
	 * @param index
	 * @param list
	 * @returns true iff index is a legal position for adding a new
	 *    data element to the list. This includes one past the last
	 *    element currently in the list.
	 */
	public static void checkForLegalAddPosition(int index, List<?> list) {
		if (!isInRange(index, 0, list.size()))
			throw new IllegalArgumentException("index out of legal range");
	}
	
	/**
	 * 
	 * @param index
	 * @param list
	 * @returns true iff index is a legal index into the array
	 */
	public static void checkForLegalIndex(int index, Object[] arr) {
		if (!isInRange(index, 0, arr.length - 1))
			throw new IllegalArgumentException("index out of legal range");
	}

	/**
	 * Static convenience methods for common tasks, which eliminate code
	 * duplication. Most return boolean. getListFromString isInRange
	 * parseBoolean textHasContent
	 * 
	 * @author <a href="http://www.javapractices.com/">javapractices.com</a>
	 *         4/06 (Hedlund) getListFromString modified for Java 1.5 template
	 *         List.
	 */

	/**
	 * Return <code>true</code> only if <code>aText</code> is not null, and
	 * is not empty after trimming. (Trimming removes both leading/trailing
	 * whitespace and ASCII control characters.)
	 * 
	 * <P>
	 * For checking argument validity, {@link Args#checkForContent} should be
	 * used instead of this method.
	 * 
	 * @param aText
	 *            possibly-null.
	 */

	public static boolean textHasContent(String aText) {
		return (aText != null) && (aText.trim().length() > 0);
	}

	/**
	 * Return <code>true</code> only if <code>aNumber</code> is in the range
	 * <code>aLow..aHigh</code> (inclusive).
	 * 
	 * <P>
	 * For checking argument validity, {@link Args#checkForRange} should be used
	 * instead of this method.
	 * 
	 * @param aLow
	 *            less than or equal to <code>aHigh</code>.
	 */
	public static boolean isInRange(int aNumber, int aLow, int aHigh) {
		if (aLow > aHigh) {
			throw new IllegalArgumentException("Low, " + aLow
					+ " is greater than High," + aHigh);
		}
		return (aLow <= aNumber && aNumber <= aHigh);
	}

	/**
	 * 
	 * @param index
	 * @param list
	 * @returns true iff index is a legal index into list
	 */
	public static boolean isIndexInRangeOfList(int index, List<?> list) {
		return isInRange(index, 0, list.size() - 1);
	}

	/**
	 * Return <code>true</code> if <code>aBoolean</code> equals "true"
	 * (ignore case), or <code>false</code> if <code>aBoolean</code> equals
	 * "false" (ignore case).
	 * 
	 * <P>
	 * Note that this behavior is different from that of
	 * <code>Boolean.getValue</code>.
	 * 
	 * @param aBoolean
	 *            equals "true" or "false" (not case-sensitive).
	 */
	public static Boolean parseBoolean(String aBoolean) {
		if (aBoolean.equalsIgnoreCase("true")) {
			return Boolean.TRUE;
		} else if (aBoolean.equalsIgnoreCase("false")) {
			return Boolean.FALSE;
		} else {
			throw new IllegalArgumentException("Cannot parse into Boolean: "
					+ aBoolean);
		}
	}

	/**
	 * Convert a <code>Collection</code> represented in the form of
	 * <code>AbstractCollection.toString</code> into a <code>List</code> of
	 * <code>String</code> objects.
	 * 
	 * <P>
	 * Intended for use as an aid in parsing collections of objects which were
	 * stored in their <code>toString</code> form. This method will not parse
	 * such <code>String</code>s into the original objects, but will aid in
	 * doing so by tokenizing it into its parts.
	 * 
	 * @param aText
	 *            has format of <code>AbstractCollection.toString</code>
	 * @return <code>String</code> objects.
	 */
	public static final List<String> getListFromString(String aText) {
		if (aText == null)
			throw new IllegalArgumentException("Text must not be null.");
		List<String> result = new ArrayList<String>();
		StringTokenizer parser = new StringTokenizer(aText, "[,] ");
		while (parser.hasMoreTokens()) {
			result.add((parser.nextToken()));
		}
		return result;
	}

}

