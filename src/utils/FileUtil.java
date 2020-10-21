package utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import constants.Constants;

public class FileUtil {

	public static String rename(String fileName) {
		String nameFile = "";
		if (!fileName.isEmpty()) {
			String[] arrImg = fileName.split("\\.");
			String duoiFileImg = arrImg[arrImg.length - 1];

			for (int i = 0; i < (arrImg.length - 1); i++) {
				if (i == 0) {
					nameFile = arrImg[i];
				} else {
					nameFile += "-" + arrImg[i];
				}
			}
			nameFile = nameFile + "-" + System.nanoTime() + "." + duoiFileImg;
		}
		return nameFile;
	}

	public static String uploadImage(String nameInput, HttpServletRequest request) {
		try {
			Part filePart = request.getPart(nameInput);
			if (filePart != null && filePart.getContentType().contains("image")) {
				String dirUpload = request.getServletContext().getRealPath("") + Constants.DIR_UPLOAD;
				File saveDir = new File(dirUpload);
				if (!saveDir.exists())
					saveDir.mkdir();
				String newFileName = rename(filePart.getSubmittedFileName());
				String filePath = dirUpload + File.separator + newFileName;
				filePart.write(filePath);
				return newFileName;
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

	public static boolean deleteImage(String name, HttpServletRequest request) {
		File image = new File(
				request.getServletContext().getRealPath("") + Constants.DIR_UPLOAD + File.separator + name);
		if (image.delete()) {
			return true;
		}
		return false;
	}

}
