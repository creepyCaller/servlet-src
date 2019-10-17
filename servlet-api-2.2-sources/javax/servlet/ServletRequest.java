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
 * ====================================================================
 *
 * This source code implements specifications defined by the Java
 * Community Process. In order to remain compliant with the specification
 * DO NOT add / change / or delete method signatures!
 */ 

package javax.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;



/**
 * Defines an object to provide client request information to a servlet.  The
 * servlet container creates a <code>ServletRequest</code> object and passes
 * it as an argument to the servlet's <code>service</code> method.
 *
 * <p>A <code>ServletRequest</code> object provides data including
 * parameter name and values, attributes, and an input stream.
 * Interfaces that extend <code>ServletRequest</code> can provide
 * additional protocol-specific data (for example, HTTP data is
 * provided by {@link javax.servlet.http.HttpServletRequest}.
 * 
 * @author 	Various
 * @version 	$Version$
 *
 * @see 	javax.servlet.http.HttpServletRequest
 *
 */

public interface ServletRequest {




    /**
     *
     * Returns the value of the named attribute as an <code>Object</code>,
     * or <code>null</code> if no attribute of the given name exists. 
     *
     * <p> Attributes can be set two ways.  The servlet container may set
     * attributes to make available custom information about a request.
     * For example, for requests made using HTTPS, the attribute
     * <code>javax.servlet.request.X509Certificate</code> can be used to
     * retrieve information on the certificate of the client.  Attributes
     * can also be set programatically using 
     * {@link ServletRequest#setAttribute}.  This allows information to be
     * embedded into a request before a {@link RequestDispatcher} call.
     *
     * <p>Attribute names should follow the same conventions as package
     * names. This specification reserves names matching <code>java.*</code>,
     * <code>javax.*</code>, and <code>sun.*</code>. 
     *
     * @param name	a <code>String</code> specifying the name of 
     *			the attribute
     *
     * @return		an <code>Object</code> containing the value 
     *			of the attribute, or <code>null</code> if
     *			the attribute does not exist
     *
     */

    public Object getAttribute(String name);
    
    

    /**
     * Returns an <code>Enumeration</code> containing the
     * names of the attributes available to this request. 
     * This method returns an empty <code>Enumeration</code>
     * if the request has no attributes available to it.
     * 
     *
     * @return		an <code>Enumeration</code> of strings 
     *			containing the names 
     * 			of the request's attributes
     *
     */

    public Enumeration getAttributeNames();
    
    
    
    
    /**
     * Returns the name of the character encoding used in the body of this
     * request. This method returns <code>null</code> if the request
     * does not specify a character encoding
     * 
     *
     * @return		a <code>String</code> containing the name of 
     *			the chararacter encoding, or <code>null</code>
     *			if the request does not specify a character encoding
     *
     */

    public String getCharacterEncoding();
    
    
    
    
    /**
     * Returns the length, in bytes, of the request body 
     * and made available by the input stream, or -1 if the
     * length is not known. For HTTP servlets, same as the value
     * of the CGI variable CONTENT_LENGTH.
     *
     * @return		an integer containing the length of the 
     * 			request body or -1 if the length is not known
     *
     */

    public int getContentLength();
    
    
    

    /**
     * Returns the MIME type of the body of the request, or 
     * <code>null</code> if the type is not known. For HTTP servlets, 
     * same as the value of the CGI variable CONTENT_TYPE.
     *
     * @return		a <code>String</code> containing the name 
     *			of the MIME type of 
     * 			the request, or -1 if the type is not known
     *
     */

    public String getContentType();
    
    
    

    /**
     * Retrieves the body of the request as binary data using
     * a {@link ServletInputStream}.  Either this method or 
     * {@link #getReader} may be called to read the body, not both.
     *
     * @return			a {@link ServletInputStream} object containing
     * 				the body of the request
     *
     * @exception IllegalStateException  if the {@link #getReader} method
     * 					 has already been called for this request
     *
     * @exception IOException    	if an input or output exception occurred
     *
     */

    public ServletInputStream getInputStream() throws IOException; 
     
    
    

    /**
     * Returns the value of a request parameter as a <code>String</code>,
     * or <code>null</code> if the parameter does not exist. Request parameters
     * are extra information sent with the request.  For HTTP servlets,
     * parameters are contained in the query string or posted form data.
     *
     * <p>You should only use this method when you are sure the
     * parameter has only one value. If the parameter might have
     * more than one value, use {@link #getParameterValues}.
     *
     * <p>If you use this method with a multivalued
     * parameter, the value returned is equal to the first value
     * in the array returned by <code>getParameterValues</code>.
     *
     * <p>If the parameter data was sent in the request body, such as occurs
     * with an HTTP POST request, then reading the body directly via {@link
     * #getInputStream} or {@link #getReader} can interfere
     * with the execution of this method.
     *
     * @param name 	a <code>String</code> specifying the 
     *			name of the parameter
     *
     * @return		a <code>String</code> representing the 
     *			single value of the parameter
     *
     * @see 		#getParameterValues
     *
     */

    public String getParameter(String name);
    
    
    

    /**
     *
     * Returns an <code>Enumeration</code> of <code>String</code>
     * objects containing the names of the parameters contained
     * in this request. If the request has 
     * no parameters, the method returns an 
     * empty <code>Enumeration</code>. 
     *
     * @return		an <code>Enumeration</code> of <code>String</code>
     *			objects, each <code>String</code> containing
     * 			the name of a request parameter; or an 
     *			empty <code>Enumeration</code> if the
     *			request has no parameters
     *
     */
     
    public Enumeration getParameterNames();
    
    
    

    /**
     * Returns an array of <code>String</code> objects containing 
     * all of the values the given request parameter has, or 
     * <code>null</code> if the parameter does not exist.
     *
     * <p>If the parameter has a single value, the array has a length
     * of 1.
     *
     * @param name	a <code>String</code> containing the name of 
     *			the parameter whose value is requested
     *
     * @return		an array of <code>String</code> objects 
     *			containing the parameter's values
     *
     * @see		#getParameter
     *
     */

    public String[] getParameterValues(String name);
    
    
    

    /**
     * Returns the name and version of the protocol the request uses
     * in the form <i>protocol/majorVersion.minorVersion</i>, for 
     * example, HTTP/1.1. For HTTP servlets, the value
     * returned is the same as the value of the CGI variable 
     * <code>SERVER_PROTOCOL</code>.
     *
     * @return		a <code>String</code> containing the protocol 
     *			name and version number
     *
     */
    
    public String getProtocol();
    
    
    

    /**
     * Returns the name of the scheme used to make this request, 
     * for example,
     * <code>http</code>, <code>https</code>, or <code>ftp</code>.
     * Different schemes have different rules for constructing URLs,
     * as noted in RFC 1738.
     *
     * @return		a <code>String</code> containing the name 
     *			of the scheme used to make this request
     *
     */

    public String getScheme();
    
    
    

    /**
     * Returns the host name of the server that received the request.
     * For HTTP servlets, same as the value of the CGI variable 
     * <code>SERVER_NAME</code>.
     *
     * @return		a <code>String</code> containing the name 
     *			of the server to which the request was sent
     */

    public String getServerName();
    
    
    

    /**
     * Returns the port number on which this request was received.
     * For HTTP servlets, same as the value of the CGI variable 
     * <code>SERVER_PORT</code>.
     *
     * @return		an integer specifying the port number
     *
     */

    public int getServerPort();
    
    
    
    /**
     * Retrieves the body of the request as character data using
     * a <code>BufferedReader</code>.  The reader translates the character
     * data according to the character encoding used on the body.
     * Either this method or {@link #getReader} may be called to read the
     * body, not both.
     * 
     *
     * @return					a <code>BufferedReader</code>
     *						containing the body of the request	
     *
     * @exception UnsupportedEncodingException 	if the character set encoding
     * 						used is not supported and the 
     *						text cannot be decoded
     *
     * @exception IllegalStateException   	if {@link #getInputStream} method
     * 						has been called on this request
     *
     * @exception IOException  			if an input or output exception occurred
     *
     * @see 					#getInputStream
     *
     */

    public BufferedReader getReader() throws IOException;
    
    
    

    /**
     * Returns the Internet Protocol (IP) address of the client 
     * that sent the request.  For HTTP servlets, same as the value of the 
     * CGI variable <code>REMOTE_ADDR</code>.
     *
     * @return		a <code>String</code> containing the 
     *			IP address of the client that sent the request
     *
     */
    
    public String getRemoteAddr();
    
    
    

    /**
     * Returns the fully qualified name of the client that sent the
     * request, or the IP address of the client if the name cannot be
     * determined. For HTTP servlets, same as the value of the CGI variable 
     * <code>REMOTE_HOST</code>.
     *
     * @return		a <code>String</code> containing the fully qualified name 
     *			of the client
     *
     */

    public String getRemoteHost();
    
    
    

    /**
     *
     * Stores an attribute in this request.
     * Attributes are reset between requests.  This method is most
     * often used in conjunction with {@link RequestDispatcher}.
     *
     * <p>Attribute names should follow the same conventions as
     * package names. Names beginning with <code>java.*</code>,
     * <code>javax.*</code>, and <code>com.sun.*</code>, are
     * reserved for use by Sun Microsystems.
     *
     *
     * @param name			a <code>String</code> specifying 
     *					the name of the attribute
     *
     * @param o				the <code>Object</code> to be stored
     *
     */

    public void setAttribute(String name, Object o);
    
    
    

    /**
     *
     * Removes an attribute from this request.  This method is not
     * generally needed as attributes only persist as long as the request
     * is being handled.
     *
     * <p>Attribute names should follow the same conventions as
     * package names. Names beginning with <code>java.*</code>,
     * <code>javax.*</code>, and <code>com.sun.*</code>, are
     * reserved for use by Sun Microsystems.
     *
     *
     * @param name			a <code>String</code> specifying 
     *					the name of the attribute to remove
     *
     */

    public void removeAttribute(String name);
    
    
    

    /**
     *
     * Returns the preferred <code>Locale</code> that the client will 
     * accept content in, based on the Accept-Language header.
     * If the client request doesn't provide an Accept-Language header,
     * this method returns the default locale for the server.
     *
     *
     * @return		the preferred <code>Locale</code> for the client
     *
     */

    public Locale getLocale();
    
    
    

    /**
     *
     * Returns an <code>Enumeration</code> of <code>Locale</code> objects
     * indicating, in decreasing order starting with the preferred locale, the
     * locales that are acceptable to the client based on the Accept-Language
     * header.
     * If the client request doesn't provide an Accept-Language header,
     * this method returns an <code>Enumeration</code> containing one 
     * <code>Locale</code>, the default locale for the server.
     *
     *
     * @return		an <code>Enumeration</code> of preferred 
     *                  <code>Locale</code> objects for the client
     *
     */

    public Enumeration getLocales();
    
    
    

    /**
     *
     * Returns a boolean indicating whether this request was made using a
     * secure channel, such as HTTPS.
     *
     *
     * @return		a boolean indicating if the request was made using a
     *                  secure channel
     *
     */

    public boolean isSecure();
    
    
    

    /**
     *
     * Returns a {@link RequestDispatcher} object that acts as a wrapper for
     * the resource located at the given path.  
     * A <code>RequestDispatcher</code> object can be used to forward
     * a request to the resource or to include the resource in a response.
     * The resource can be dynamic or static.
     *
     * <p>The pathname specified may be relative, although it cannot extend
     * outside the current servlet context.  If the path begins with 
     * a "/" it is interpreted as relative to the current context root.  
     * This method returns <code>null</code> if the servlet container
     * cannot return a <code>RequestDispatcher</code>.
     *
     * <p>The difference between this method and {@link
     * ServletContext#getRequestDispatcher} is that this method can take a
     * relative path.
     *
     * @param path      a <code>String</code> specifying the pathname
     *                  to the resource
     *
     * @return          a <code>RequestDispatcher</code> object
     *                  that acts as a wrapper for the resource
     *                  at the specified path
     *
     * @see             RequestDispatcher
     * @see             ServletContext#getRequestDispatcher
     *
     */

    public RequestDispatcher getRequestDispatcher(String path);
    
    
    

    /**
     * 
     * @deprecated 	As of Version 2.1 of the Java Servlet API,
     * 			use {@link ServletContext#getRealPath} instead.
     *
     */

    public String getRealPath(String path);
    
    
}

