package java8;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.junit.Test;

public class _7_Try {
	void tryNotAutoCloseable() {
		try (String resource = "") {

		}
	}

	void tryWithManyAutoCloseable(String zipFileName, Path outputFilePath) {
		try (ZipFile zf = new ZipFile(zipFileName);
			 BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8)) {

			for (Enumeration<? extends ZipEntry> entries = zf.entries(); entries.hasMoreElements();) {
				String zipEntryName = ((ZipEntry) entries.nextElement()).getName();
				writer.write(zipEntryName, 0, zipEntryName.length());
			}

		}
	}

	static class MyAutoCloseable implements AutoCloseable {

		MyAutoCloseable() {
			
		}
		static MyAutoCloseable withException() throws Exception {
			throw new Exception();
		}
		@Override
		public void close() throws Exception {
			System.out.println("close");
			throw new Exception();
		}

	}

	@Test
	public void orderOfStatements() {
		try (MyAutoCloseable resource = new MyAutoCloseable()) {
			System.out.println("Inside try");
		} catch (Exception e) {
			System.out.println("Inside catch");
			e.printStackTrace(System.out);
		} finally {
			System.out.println("Inside finally");
		}

	}
	
	@Test
	public void orderOfStatements_exceptionInConstructor() {
		try (MyAutoCloseable resource = MyAutoCloseable.withException()) {
			System.out.println("Inside try");
		} catch (Exception e) {
			System.out.println("Inside catch");
			e.printStackTrace(System.out);
		} finally {
			System.out.println("Inside finally");
		}

	}

}
