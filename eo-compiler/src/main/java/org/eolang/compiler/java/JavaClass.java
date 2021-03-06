/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 eolang.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.eolang.compiler.java;

import java.util.Collection;
import org.cactoos.Input;
import org.cactoos.io.InputOf;
import org.cactoos.text.FormattedText;
import org.cactoos.text.JoinedText;
import org.cactoos.text.UncheckedText;
import org.eolang.compiler.syntax.ObjectBody;

/**
 * File with java class.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class JavaClass implements JavaFile {

    /**
     * Class name.
     */
    private final String name;

    /**
     * Implemented interface name.
     */
    private final Collection<String> ifaces;

    /**
     * Object body: constructors, attributes, methods.
     */
    private final ObjectBody body;

    /**
     * Ctor.
     *
     * @param name Class name
     * @param ifaces Implemented interfaces name
     * @param body Object body
     */
    @SuppressWarnings("checkstyle:parameternumbercheck")
    public JavaClass(final String name, final Collection<String> ifaces,
        final ObjectBody body) {
        this.name = name;
        this.ifaces = ifaces;
        this.body = body;
    }

    @Override
    public String path() {
        return new UncheckedText(
            new FormattedText("%s.java", this.name)
        ).asString();
    }

    @Override
    public Input code() {
        return new InputOf(
            new FormattedText(
                "%s\n\npublic final class %s implements %s {\n  %s\n}\n",
                "package eo;",
                this.name,
                new UncheckedText(
                    new JoinedText(", ", this.ifaces)
                ).asString(),
                this.body.java(this.name).replace("\n", "\n  ")
            )
        );
    }
}
