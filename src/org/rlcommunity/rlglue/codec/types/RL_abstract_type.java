/* 
 * Copyright (C) 2007, Brian Tanner
 *
http://rl-glue-ext.googlecode.com/

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 *
 *  $Revision$
 *  $Date$
 *  $Author$
 *  $HeadURL$
 *
 */
package org.rlcommunity.rlglue.codec.types;

import java.util.Arrays;

/**
 * Common superclass for all of the Java RL-Glue types.
 * Try to keep handles to the objects and not their arrays,
 * because there is no guarantee that the arrays will not be
 * reallocated during certain operations.
 *
 * @author btanner
 */
public class RL_abstract_type implements Comparable<RL_abstract_type> {

    public int[] intArray = null;
    public double[] doubleArray = null;
    public char[] charArray = null;

    /**
     * Create a RL_abstract_type with arrays allocated according to numIntegers,
     * numDoubles, and numChars
     *
     * @param numIntegers Size of int array to allocate.
     * @param numDoubles  Size of double array to allocate.
     * @param numChars    Size of char array to allocate.
     */
    public RL_abstract_type(int numIntegers, int numDoubles, int numChars) {
        intArray = new int[numIntegers];
        doubleArray = new double[numDoubles];
        charArray = new char[numChars];
    }

    /**
     * Create a new RL_abstract_type that is a deep, independent copy of src.
     *
     * @param src
     */
    public RL_abstract_type(RL_abstract_type src) {
        this(src.intArray.length, src.doubleArray.length, src.charArray.length);
        RLStructCopy(src, this);
    }

    public int getInt(int which) {
        return this.intArray[which];
    }

    public double getDouble(int which) {
        return this.doubleArray[which];
    }

    public char getChar(int which) {
        return this.charArray[which];
    }

    public void setInt(int which, int value) {
        assert (intArray != null && which < intArray.length) : "Tried to set int: " + which + " but intArray only has length " + intArray.length + ".";
        intArray[which] = value;
    }

    public void setDouble(int which, double value) {
        assert (doubleArray != null && which < doubleArray.length) : "Tried to set double: " + which + " but doubleArray only has length " + doubleArray.length + ".";
        doubleArray[which] = value;
    }

    public void setChar(int which, char value) {
        assert (charArray != null && which < charArray.length) : "Tried to set char: " + which + " but charArray only has length " + charArray.length + ".";
        charArray[which] = value;
    }

    public int getNumIntegersLength() {
        if (intArray == null) {
            intArray = new int[0];
        }
        return intArray.length;
    }

    public int getNumDoublesLength() {
        if (doubleArray == null) {
            doubleArray = new double[0];
        }
        return doubleArray.length;
    }

    public int getNumCharsLength() {
        if (charArray == null) {
            charArray = new char[0];
        }
        return charArray.length;
    }

    /**
     * Useful (maybe?) utility method for deep copying one RL_Abstract_type
     * into another.
     *
     * @param src
     * @param dest
     */
    public static void RLStructCopy(RL_abstract_type src, RL_abstract_type dest) {
        if (src == null || dest == null) {
            throw new IllegalArgumentException("Either src or dest RL_Abstract_Type was null in RLStructCopy");
        }
        if (dest.intArray.length != src.intArray.length) {
            dest.intArray = new int[src.intArray.length];
        }
        if (dest.doubleArray.length != src.doubleArray.length) {
            dest.doubleArray = new double[src.doubleArray.length];
        }
        if (dest.charArray.length != src.charArray.length) {
            dest.charArray = new char[src.charArray.length];
        }

        System.arraycopy(src.intArray, 0, dest.intArray, 0, src.intArray.length);
        System.arraycopy(src.doubleArray, 0, dest.doubleArray, 0, src.doubleArray.length);
        System.arraycopy(src.charArray, 0, dest.charArray, 0, src.charArray.length);
    }

    /**
     * Prints out a human-readable format of the RL_abstract_type, which is
     * useful for debugging.
     */
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("numInts: ").append(intArray.length);
        b.append("\n");
        b.append("numDoubles: ").append(doubleArray.length);
        b.append("\n");
        b.append("numChars: ").append(charArray.length);
        b.append("\n");
        if (intArray != null) {
            for (int anIntArray : intArray) {
                b.append(" ").append(anIntArray);
            }
        }
        for (double aDoubleArray : doubleArray) {
            b.append(" ").append(aDoubleArray);
        }
        for (char aCharArray : charArray) {
            b.append(" ").append(aCharArray);
        }

        return b.toString();
    }

    /**
     * Allows us to easily compare abstract types so that we can put them
     * in maps and stuff.
     *
     * @param other The object to compare against
     * @return -1 if this is 'smaller' then other, +1 if this is 'bigger' than other, 0 if they are identical.
     */
    public int compareTo(RL_abstract_type other) {

        if (intArray == null & other.intArray != null) {
            return -1;
        }
        if (intArray != null & other.intArray == null) {
            return 1;
        }

        if (intArray != null & other.intArray != null) {
            if (intArray.length < other.intArray.length) {
                return -1;
            }
            if (intArray.length > other.intArray.length) {
                return 1;
            }

            for (int i = 0; i < intArray.length; i++) {
                if (intArray[i] < other.intArray[i]) {
                    return -1;
                }
                if (intArray[i] > other.intArray[i]) {
                    return 1;
                }
            }
        }
        if (doubleArray == null & other.doubleArray != null) {
            return -1;
        }
        if (doubleArray != null & other.doubleArray == null) {
            return 1;
        }

        if (doubleArray != null & other.doubleArray != null) {
            if (doubleArray.length < other.doubleArray.length) {
                return -1;
            }
            if (doubleArray.length > other.doubleArray.length) {
                return 1;
            }

            for (int i = 0; i < doubleArray.length; i++) {
                if (doubleArray[i] < other.doubleArray[i]) {
                    return -1;
                }
                if (doubleArray[i] > other.doubleArray[i]) {
                    return 1;
                }
            }
        }

        if (charArray == null & other.charArray != null) {
            return -1;
        }
        if (charArray != null & other.charArray == null) {
            return 1;
        }

        if (charArray != null & other.charArray != null) {
            if (charArray.length < other.charArray.length) {
                return -1;
            }
            if (charArray.length > other.charArray.length) {
                return 1;
            }

            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] < other.charArray[i]) {
                    return -1;
                }
                if (charArray[i] > other.charArray[i]) {
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + Arrays.hashCode(this.charArray);
        result = prime * result + Arrays.hashCode(this.intArray);
        result = prime * result + Arrays.hashCode(this.doubleArray);

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        RL_abstract_type other = (RL_abstract_type) obj;
        return Arrays.equals(this.charArray, other.charArray)
                && Arrays.equals(this.doubleArray, other.doubleArray)
                && Arrays.equals(this.intArray, other.intArray);

    }
}
