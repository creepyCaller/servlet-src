/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:  
 *       "This product includes software developed by the 
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */ 
 
package javax.servlet.jsp;

import java.io.IOException;

/**
 * <p>
 * This abstract class emulates some of the functionality found in the
 * java.io.BufferedWriter and java.io.PrintWriter classes,
 * however it differs in that it throws java.io.IOException from the print
 * methods with PrintWriter does not.
 * </p>
 * <p>
 * The "out" implicit variable of a JSP implementation class is of this type.
 * If the page directive selects autoflush="true" then all the I/O operations
 * on this class shall automatically fluch the contents of the buffer if an
 * overflow condition would result if the current operation were performed
 * without a flush. If autoflush="false" then all the I/O operations on this
 * class shall throw an IOException if performing the current opertion would
 * result in a buffer overflow condition.
 * </p>
 *
 * @see java.io.Writer
 * @see java.io.BufferedWriter
 * @see java.io.PrintWriter
 */

abstract public class JspWriter extends java.io.Writer {

    /**
     * constant indicating that the Writer is not buffering output
     */

    public static final int	NO_BUFFER = 0;

    /**
     * constant indicating that the Writer is buffered and is using the implementation default buffer size
     */

    public static final int	DEFAULT_BUFFER = -1;

    /**
     * constant indicating that the Writer is buffered and is unbounded; this is used in BodyContent
     */

    public static final int	UNBOUNDED_BUFFER = -2;

    /**
     * protected constructor.
     */

    protected JspWriter(int bufferSize, boolean autoFlush) {
	this.bufferSize = bufferSize;
	this.autoFlush  = autoFlush;
    }

    /**
     * Write a line separator.  The line separator string is defined by the
     * system property <tt>line.separator</tt>, and is not necessarily a single
     * newline ('\n') character.
     *
     * @exception  IOException  If an I/O error occurs
     */

    abstract public void newLine() throws IOException;

    /**
     * Print a boolean value.  The string produced by <code>{@link
     * java.lang.String#valueOf(boolean)}</code> is translated into bytes
     * according to the platform's default character encoding, and these bytes
     * are written in exactly the manner of the <code>{@link
     * #write(int)}</code> method.
     *
     * @param      b   The <code>boolean</code> to be printed
     * @throws	   java.io.IOException
     */

    abstract public void print(boolean b) throws IOException;

    /**
     * Print a character.  The character is translated into one or more bytes
     * according to the platform's default character encoding, and these bytes
     * are written in exactly the manner of the <code>{@link
     * #write(int)}</code> method.
     *
     * @param      c   The <code>char</code> to be printed
     * @throws	   java.io.IOException
     */

    abstract public void print(char c) throws IOException;

    /**
     * Print an integer.  The string produced by <code>{@link
     * java.lang.String#valueOf(int)}</code> is translated into bytes according
     * to the platform's default character encoding, and these bytes are
     * written in exactly the manner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @param      i   The <code>int</code> to be printed
     * @see        java.lang.Integer#toString(int)
     * @throws	   java.io.IOException
     */

    abstract public void print(int i) throws IOException;

    /**
     * Print a long integer.  The string produced by <code>{@link
     * java.lang.String#valueOf(long)}</code> is translated into bytes
     * according to the platform's default character encoding, and these bytes
     * are written in exactly the manner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @param      l   The <code>long</code> to be printed
     * @see        java.lang.Long#toString(long)
     * @throws	   java.io.IOException
     */

    abstract public void print(long l) throws IOException;

    /**
     * Print a floating-point number.  The string produced by <code>{@link
     * java.lang.String#valueOf(float)}</code> is translated into bytes
     * according to the platform's default character encoding, and these bytes
     * are written in exactly the manner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @param      f   The <code>float</code> to be printed
     * @see        java.lang.Float#toString(float)
     * @throws	   java.io.IOException
     */

    abstract public void print(float f) throws IOException;

    /**
     * Print a double-precision floating-point number.  The string produced by
     * <code>{@link java.lang.String#valueOf(double)}</code> is translated into
     * bytes according to the platform's default character encoding, and these
     * bytes are written in exactly the manner of the <code>{@link
     * #write(int)}</code> method.
     *
     * @param      d   The <code>double</code> to be printed
     * @see        java.lang.Double#toString(double)
     * @throws	   java.io.IOException
     */

    abstract public void print(double d) throws IOException;

    /**
     * Print an array of characters.  The characters are converted into bytes
     * according to the platform's default character encoding, and these bytes
     * are written in exactly the manner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @param      s   The array of chars to be printed
     *
     * @throws  NullPointerException  If <code>s</code> is <code>null</code>
     * @throws	   java.io.IOException
     */

    abstract public void print(char s[]) throws IOException;

    /**
     * Print a string.  If the argument is <code>null</code> then the string
     * <code>"null"</code> is printed.  Otherwise, the string's characters are
     * converted into bytes according to the platform's default character
     * encoding, and these bytes are written in exactly the manner of the
     * <code>{@link #write(int)}</code> method.
     *
     * @param      s   The <code>String</code> to be printed
     * @throws	   java.io.IOException
     */

    abstract public void print(String s) throws IOException;

    /**
     * Print an object.  The string produced by the <code>{@link
     * java.lang.String#valueOf(Object)}</code> method is translated into bytes
     * according to the platform's default character encoding, and these bytes
     * are written in exactly the manner of the <code>{@link #write(int)}</code>
     * method.
     *
     * @param      obj   The <code>Object</code> to be printed
     * @see        java.lang.Object#toString()
     * @throws	   java.io.IOException
     */

    abstract public void print(Object obj) throws IOException;

    /**
     * Terminate the current line by writing the line separator string.  The
     * line separator string is defined by the system property
     * <code>line.separator</code>, and is not necessarily a single newline
     * character (<code>'\n'</code>).
     * @throws	   java.io.IOException
     */

    abstract public void println() throws IOException;

    /**
     * Print a boolean value and then terminate the line.  This method behaves
     * as though it invokes <code>{@link #print(boolean)}</code> and then
     * <code>{@link #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(boolean x) throws IOException;

    /**
     * Print a character and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(char)}</code> and then <code>{@link
     * #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(char x) throws IOException;

    /**
     * Print an integer and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(int)}</code> and then <code>{@link
     * #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(int x) throws IOException;

    /**
     * Print a long integer and then terminate the line.  This method behaves
     * as though it invokes <code>{@link #print(long)}</code> and then
     * <code>{@link #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(long x) throws IOException;

    /**
     * Print a floating-point number and then terminate the line.  This method
     * behaves as though it invokes <code>{@link #print(float)}</code> and then
     * <code>{@link #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(float x) throws IOException;

    /**
     * Print a double-precision floating-point number and then terminate the
     * line.  This method behaves as though it invokes <code>{@link
     * #print(double)}</code> and then <code>{@link #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(double x) throws IOException;

    /**
     * Print an array of characters and then terminate the line.  This method
     * behaves as though it invokes <code>{@link #print(char[])}</code> and then
     * <code>{@link #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(char x[]) throws IOException;

    /**
     * Print a String and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(String)}</code> and then
     * <code>{@link #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(String x) throws IOException;

    /**
     * Print an Object and then terminate the line.  This method behaves as
     * though it invokes <code>{@link #print(Object)}</code> and then
     * <code>{@link #println()}</code>.
     * @throws	   java.io.IOException
     */

    abstract public void println(Object x) throws IOException;

    /**
     * Clear the contents of the buffer. If the buffer has been already
     * been flushed then the clear operation shall throw an IOException
     * to signal the fact that some data has already been irrevocably 
     * written to the client response stream.
     *
     * @throws IOException		If an I/O error occurs
     */

    abstract public void clear() throws IOException;

    /**
     * Clears the current contents of the buffer. Unlike clear(), this
     * mehtod will not throw an IOException if the buffer has already been
     * flushed. It merely clears the current content of the buffer and
     * returns.
     *
     * @throws IOException		If an I/O error occurs
     */

    abstract public void clearBuffer() throws IOException;

    /**
     * Flush the stream.  If the stream has saved any characters from the
     * various write() methods in a buffer, write them immediately to their
     * intended destination.  Then, if that destination is another character or
     * byte stream, flush it.  Thus one flush() invocation will flush all the
     * buffers in a chain of Writers and OutputStreams.
     *
     * @exception  IOException  If an I/O error occurs
     */

    abstract public void flush() throws IOException;

    /**
     * Close the stream, flushing it first.  Once a stream has been closed,
     * further write() or flush() invocations will cause an IOException to be
     * thrown.  Closing a previously-closed stream, however, has no effect.
     *
     * @exception  IOException  If an I/O error occurs
     */

    abstract public void close() throws IOException;

    /**
     * @return the size of the buffer in bytes, or 0 is unbuffered.
     */

    public int getBufferSize() { return bufferSize; }

    /**
     * @return the number of bytes unused in the buffer
     */

    abstract public int getRemaining();

    /**
     * @return if this JspWriter is auto flushing or throwing IOExceptions on buffer overflow conditions
     */

    public boolean isAutoFlush() { return autoFlush; }

    /*
     * fields
     */

    protected int     bufferSize;
    protected boolean autoFlush;
}
