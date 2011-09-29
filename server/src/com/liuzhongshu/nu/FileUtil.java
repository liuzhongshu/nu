package com.liuzhongshu.nu;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUtil {
	
	public static File createTempDirectory()   throws IOException
	{
	    final File temp;

	    temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

	    if(!(temp.delete()))
	    {
	        throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
	    }

	    if(!(temp.mkdir()))
	    {
	        throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
	    }

	    return (temp);
	}
	
	public static void copyPipe(InputStream in,OutputStream out,int bufSizeHint )
		    throws IOException {

		int read = -1;
	    byte[] buf = new byte[bufSizeHint];
	    while ( ( read = in.read( buf, 0, bufSizeHint ) ) >= 0 )
	    {
	      out.write( buf, 0, read );
	    }
	    out.flush();
	  }
	
	public static void unzip(File zipFile, File destDir) throws IOException {
		ZipFile zip = new ZipFile(zipFile);
		Enumeration en = zip.entries();
		int bufSize = 8196;

		while (en.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) en.nextElement();
			File file = (destDir != null) ? new File(destDir, entry.getName()) : new File(entry.getName());
			if (entry.isDirectory()) {
				if (!file.mkdirs()) {
					if (file.isDirectory() == false) {
						throw new IOException("Error creating directory: " + file);
					}
				}
			} else {
				File parent = file.getParentFile();
				if (parent != null && !parent.exists()) {
					if (!parent.mkdirs()) {
						if (file.isDirectory() == false) {
							throw new IOException("Error creating directory: " + parent);
						}
					}
				}

				InputStream in = zip.getInputStream(entry);
				try {
					OutputStream out = new BufferedOutputStream(new FileOutputStream(file), bufSize);
					try {
						copyPipe(in, out, bufSize);
					} finally {
						out.close();
					}
				} finally {
					in.close();
				}
			}
		}
	}
	

}
