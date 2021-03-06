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

import org.cactoos.list.StickyList;
import org.eolang.compiler.syntax.Attribute;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Java class primary constructor test.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PrimaryConstructorTest {

    /**
     * Generates constructor code with fields initialization.
     */
    @Test
    public void generatesJavaCode() {
        MatcherAssert.assertThat(
            new PrimaryConstructor(
                "color",
                new StickyList<>(
                    new Attribute("Byte", "alpha"),
                    new Attribute("Int", "rgb")
                )
            ).code(),
            Matchers.stringContainsInOrder(
                new StickyList<>(
                    "public",
                    "color(",
                    "final Byte alpha",
                    "final Int rgb",
                    ")",
                    "{",
                    "this.alpha = alpha;",
                    "this.rgb = rgb;",
                    "}"
                )
            )
        );
    }
}
