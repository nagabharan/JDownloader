import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class UrlDownload {
	final static int size = 1024;

	public static void fileUrl(String fAddress, String localFileName,
			String destinationDir) {
		OutputStream outStream = null;
		URLConnection uCon = null;

		InputStream is = null;
		try {
			URL Url;
			byte[] buf;
			int ByteRead, ByteWritten = 0, fsize = 0;
			Url = new URL(fAddress);

			outStream = new BufferedOutputStream(new FileOutputStream(
					destinationDir + "\\" + localFileName));

			uCon = Url.openConnection();
			is = uCon.getInputStream();
			fsize = uCon.getContentLength();
			buf = new byte[size];
			System.out.print("\nPercentage done:");
			while ((ByteRead = is.read(buf)) != -1) {
				outStream.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
				System.out.print(Math
						.round((((float) ByteWritten / (float) fsize) * 100))
						+ "% ");
			}
			System.out.println("\nDownloaded Successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void fileDownload(String fAddress, String destinationDir) {
		int slashIndex = fAddress.lastIndexOf('/');
		int periodIndex = fAddress.lastIndexOf('.');

		String fileName = fAddress.substring(slashIndex + 1);

		if (periodIndex >= 1 && slashIndex >= 0
				&& slashIndex < fAddress.length() - 1) {
			fileUrl(fAddress, fileName, destinationDir);
		} else {
			System.err.println("path or file name.");
		}
	}

	public static void main(String[] args) throws IOException {

		String url = null, dest = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter Url:");
		url = br.readLine();
		System.out.print("Enter Destination folder:");
		dest = br1.readLine();
		fileDownload(url, dest);

	}
}