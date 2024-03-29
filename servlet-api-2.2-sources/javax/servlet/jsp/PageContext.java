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

import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.jsp.tagext.BodyContent;

/**
 * <p>
 * A PageContext instance provides access to all the namespaces associated with
 * a JSP page, provides access to several page attributes, as well as a layer above the
 * implementation details.
 * <p>
 * An instance of an implementation dependent subclass of this abstract base
 * class is created by a JSP implementation class at the begining of it's
 * <code> _jspService() </code> method via an implementation default 
 * <code> JspFactory </code>, as follows:
 *</p>
 *<p>
 *<p>
 *<code>
 *<pre>
 * public class foo implements Servlet {
 *
 * // ...
 *
 *public void _jspService(HttpServletRequest request,
 *			HttpServletResponse response)
 *       throws IOException, ServletException {
 *
 *    JspFactory  factory     = JspFactory.getDefaultFactory();
 *    PageContext pageContext = factory.getPageContext(
					this,
					request,
					response,
					null,  // errorPageURL
					false, // needsSession
					JspWriter.DEFAULT_BUFFER,
					true   // autoFlush
			        );
 *
 *    // initialize implicit variables for scripting env ...
 *
 *    HttpSession session = pageContext.getSession();
 *    JspWriter   out     = pageContext.getOut();
 *    Object      page    = this;
 *
 *    try {
 *        // body of translated JSP here ...
 *    } catch (Exception e) {
 *        out.clear();
 *        pageContext.handlePageException(e);
 *    } finally {
 *        out.close();
 *	  factory.releasePageContext(pageContext);
 *    }
 *}
 *</pre>
 * </code>
 * </p>
 * <p>
 * The <code> PageContext </code> class is an abstract class, designed to be
 * extended to provide implementation dependent implementations thereof, by
 * conformant JSP engine runtime environments. A PageContext instance is 
 * obtained by a JSP implementation class by calling the JspFactory.getPageContext() method, and is released by calling JspFactory.releasePageContext().
 * </p>
 * <p>
 * The PageContext provides a number of facilities to the page/component author and
 * page implementor, including:
 * <td>
 * <li>a single API to manage the various scoped namespaces
 * <li>a number of convenience API's to access various public objects
 * <li>a mechanism to obtain the JspWriter for output
 * <li>a mechanism to manage session usage by the page
 * <li>a mechanism to expose page directive attributes to the scripting environment
 * <li>mechanisms to forward or include the current request to other active components in the application
 * <li>a mechanism to handle errorpage exception processing
 * </td>
 * </p>
 *
 */

abstract public class PageContext {

    /**
     * page scope: (this is the default) the named reference remains available
     * in this PageContext until the return from the current Servlet.service()
     * invocation.
     */

    public static final int PAGE_SCOPE		= 1;

    /**
     * request scope: the named reference remains available from the ServletRequest
     * associated with the Servlet that until the current request is completed.
     */

    public static final int REQUEST_SCOPE	= 2;

    /**
     * session scope (only valid if this page participates in a session):
     * the named reference remains available from the HttpSession (if any)
     * associated with the Servlet until the HttpSession is invalidated.
     */

    public static final int SESSION_SCOPE	= 3;

    /**
     * application scope: named reference remains available in the 
     * ServletContext until it is reclaimed.
     */

    public static final int APPLICATION_SCOPE	= 4;

    /**
     * name used to store the Servlet in this PageContext's nametables
     */

    public static final String	PAGE		= "javax.servlet.jsp.jspPage";

    /**
     * name used to store this PageContext in it's own name tables
     */

    public static final String	PAGECONTEXT	= "javax.servlet.jsp.jspPageContext";

    /**
     * name used to store ServletRequest in PageContext name table
     */

    public static final String	REQUEST		= "javax.servlet.jsp.jspRequest";

    /**
     * name used to store ServletResponse in PageContext name table
     */

    public static final String	RESPONSE	= "javax.servlet.jsp.jspResponse";

    /**
     * name used to store ServletConfig in PageContext name table
     */

    public static final String	CONFIG		= "javax.servlet.jsp.jspConfig";

    /**
     * name used to store HttpSession in PageContext name table
     */

    public static final String	SESSION		= "javax.servlet.jsp.jspSession";
    /**
     * name used to store current JspWriter in PageContext name table
     */

    public static final String	OUT		= "javax.servlet.jsp.jspOut";

    /**
     * name used to store ServletContext in PageContext name table
     */

    public static final String	APPLICATION	= "javax.servlet.jsp.jspApplication";

    /**
     * name used to store uncaught exception in ServletRequest attribute list and PageContext name table
     */

    public static final String	EXCEPTION	= "javax.servlet.jsp.jspException";

    /**
     * <p>
     * The initialize emthod is called to initialize an uninitialized PageContext
     * so that it may be used by a JSP Implementation class to service an
     * incoming request and response wihtin it's _jspService() method.
     * </p>
     * <p>
     * This method is typically called from JspFactory.getPageContext() in
     * order to initialize state.
     * </p>
     * <p>
     * This method is required to create an initial JspWriter, and associate
     * the "out" name in page scope with this newly created object.
     * </p>
     *
     * @param servlet The Servlet that is associated with this PageContext
     * @param request The currently pending request for this Servlet
     * @param response The currently pending response for this Servlet
     * @param errorPageURL The value of the errorpage attribute from the page directive or null
     * @param needsSession The value of the session attribute from the page directive
     * @param bufferSize The value of the buffer attribute from the page directive
     * @param autoFlush The value of the autoflush attribute from the page directive
     *
     * @throws IOException during creation of JspWriter
     * @throws IllegalStateException if out not correctly initialized
     */
 
    abstract public void initialize(Servlet servlet, ServletRequest request, ServletResponse response, String errorPageURL, boolean needsSession, int bufferSize, boolean autoFlush)  throws IOException, IllegalStateException, IllegalArgumentException;

    /**
     * <p>
     * This method shall "reset" the internal state of a PageContext, releasing
     * all internal references, and preparing the PageContext for potential
     * reuse by a later invocation of initialize(). This method is typically
     * called from JspFactory.releasePageContext().
     * </p>
     *
     * <p> subclasses shall envelope this method </p>
     *
     */

    abstract public void release();

    /**
     * register the name and object specified with page scope semantics
     * 
     * @throws NullPointerException if the name or object is null
     */

    abstract public void setAttribute(String name, Object attribute);

    /**
     * register the name and object specified with appropriate scope semantics
     * 
     * @param name the name of the attribute to set
     * @param o    the object to associate with the name
     * @param scope the scope with which to associate the name/object
     * 
     * @throws NullPointerException if the name or object is null
     * @throws IllegalArgumentException if the scope is invalid
     *
     */

    abstract public void setAttribute(String name, Object o, int scope);

    /**
     * <p>return the object associated with the name in the page scope or null </p>
     *
     * @param name the name of the attribute to get
     * 
     * @throws NullPointerException if the name is null
     * @throws IllegalArgumentException if the scope is invalid
     */

    abstract public Object getAttribute(String name);

    /**
     * <p>return the object associated with the name in the specifed scope or null </p>
     *
     * @param name the name of the attribute to set
     * @param scope the scope with which to associate the name/object
     * 
     * @throws NullPointerException if the name is null
     * @throws IllegalArgumentException if the scope is invalid
     */

    abstract public Object getAttribute(String name, int scope);

    /**
     * <p>
     * Searches for the named attribute in page, request, session (if valid),
     * and application scope(s) in order and returns the value associated or
     * null.
     * </p>
     *
     * @return the value associated or null
     */

    abstract public Object findAttribute(String name);

    /**
     * remove the object reference associated with the specified name
     */

    abstract public void removeAttribute(String name);

    /**
     * remove the object reference associated with the specified name
     */

    abstract public void removeAttribute(String name, int scope);

    /**
     * @return the scope of the object associated with the name specified or 0
     */

    abstract public int getAttributesScope(String name);

    /**
     * @return an enumeration of names (java.lang.String) of all the attributes the specified scope
     */


    abstract public Enumeration getAttributeNamesInScope(int scope);

    /**
     * @return the current JspWriter stream being used for client response
     */

    abstract public JspWriter getOut();

    /**
     * @return the HttpSession for this PageContext or null
     */

    abstract public HttpSession getSession();

    /**
     * @return the Page implementation class instance (Servlet)  associated with this PageContext
     */

    abstract public Object getPage();


    /**
     * @return The ServletRequest for this PageContext
     */

    abstract public ServletRequest getRequest();

    /**
     * @return the ServletResponse for this PageContext
     */

    abstract public ServletResponse getResponse();

    /**
     * @return any exception passed to this as an errorpage
     */

    abstract public Exception getException();

    /**
     * @return the ServletConfig for this PageContext
     */

    abstract public ServletConfig getServletConfig();

    /**
     * @return the ServletContext for this PageContext
     */

    abstract public ServletContext getServletContext();

    /**
     * <p>
     * This method is used to re-direct, or "forward" the current ServletRequest and ServletResponse to another active component in the application.
     * </p>
     * <p>
     * If the <I> relativeUrlPath </I> begins with a "/" then the URL specified
     * is calculated relative to the DOCROOT of the <code> ServletContext </code>
     * for this JSP. If the path does not begin with a "/" then the URL 
     * specified is calculated relative to the URL of the request that was
     * mapped to the calling JSP.
     * </p>
     * <p>
     * It is only valid to call this method from a <code> Thread </code>
     * executing within a <code> _jspService(...) </code> method of a JSP.
     * </p>
     * <p>
     * Once this method has been called successfully, it is illegal for the
     * calling <code> Thread </code> to attempt to modify the <code>
     * ServletResponse </code> object.  Any such attempt to do so, shall result
     * in undefined behavior. Typically, callers immediately return from 
     * <code> _jspService(...) </code> after calling this method.
     * </p>
     *
     * @param relativeUrlPath specifies the relative URL path to the target resource as described above
     *
     * @throws ServletException
     * @throws IOException
     *
     * @throws IllegalArgumentException if target resource URL is unresolvable
     * @throws IllegalStateException if <code> ServletResponse </code> is not in a state where a forward can be performed
     * @throws SecurityException if target resource cannot be accessed by caller
     */

    abstract public void forward(String relativeUrlPath) throws ServletException, IOException;

    /**
     * <p>
     * Causes the resource specified to be processed as part of the current
     * ServletRequest and ServletResponse being processed by the calling Thread.
     * The output of the target resources processing of the request is written
     * directly to the ServletResponse output stream.
     * </p>
     * <p>
     * The current JspWriter "out" for this JSP is flushed as a side-effect
     * of this call, prior to processing the include.
     * </p>
     * <p>
     * If the <I> relativeUrlPath </I> begins with a "/" then the URL specified
     * is calculated relative to the DOCROOT of the <code> ServletContext </code>
     * for this JSP. If the path does not begin with a "/" then the URL 
     * specified is calculated relative to the URL of the request that was
     * mapped to the calling JSP.
     * </p>
     * <p>
     * It is only valid to call this method from a <code> Thread </code>
     * executing within a <code> _jspService(...) </code> method of a JSP.
     * </p>
     *
     * @param relativeUrlPath specifies the relative URL path to the target resource to be included
     *
     * @throws ServletException
     * @throws IOException
     *
     * @throws IllegalArgumentException if the target resource URL is unresolvable
     * @throws SecurityException if target resource cannot be accessed by caller
     *
     */

    abstract public void include(String relativeUrlPath) throws ServletException, IOException;

    /**
     * <p>
     * This method is intended to process an unhandled "page" level exception
     * by redirecting the exception to either the specified error page for this
     * JSP, or if none was specified, to perform some implementation dependent
     * action.
     * </p>
     * <p>
     * A JSP implementation class shall typically clean up any local state
     * prior to invoking this and will return immediately thereafter. It is
     * illegal to generate any output to the client, or to modify any 
     * ServletResponse state after invoking this call.
     * </p>
     *
     * @param e the exception to be handled
     *
     * @throws ServletException
     * @throws IOException
     *
     * @throws NullPointerException if the exception is null
     * @throws SecurityException if target resource cannot be accessed by caller
     */

    abstract public void handlePageException(Exception e) throws ServletException, IOException;

    /**
     * Return a new BodyContent object, save the current "out" JspWriter,
     * and update the value of the "out" attribute in the page scope
     * attribute namespace of the PageContext
     *
     * @return the new BodyContent
     */

    public BodyContent pushBody() {
        return null; // XXX to implement
    }
         

    /**
     * Return the previous JspWriter "out" saved by the matching
     * pushBody(), and update the value of the "out" attribute in
     * the page scope attribute namespace of the PageConxtext
     *
     * @return the saved JspWriter.
     */

    public JspWriter popBody() {
        return null; // XXX to implement
    }
    
}
