/**
 * Copyright © 2012 Joe Littlejohn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.joelittlejohn.embedmongo;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author rianmachado@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class LocalDirDecoratorTest {

	private LocalDirBinaryMongo LocalDirBinaryMongo;

	@Before
	public void init() {
		LocalDirBinaryMongo = new LocalDirBinaryMongo();
	}

	@Test
	public void testBuildPathInputDir() throws IOException {
		LocalDirDecorator wrappee = new LocalDirDecorator(LocalDirBinaryMongo);
		assertNotNull(wrappee.buildPathInputDir());
	}

	@Test
	public void testeBuildPathOutputDir() throws IOException {
		LocalDirDecorator wrappee = new LocalDirDecorator(LocalDirBinaryMongo);
		assertNotNull(wrappee.buildPathOutputDir());
	}

}
