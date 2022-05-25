package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.predicates.FileExtensionsPredicate;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidDirectoryException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import com.epam.engx.cleancode.naming.task5.thirdpartyjar.PropertyUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileManager {

    private static final String[] IMAGE_VALID_EXTENSIONS = {"jpg", "png"};
    private static final String[] FILE_VALID_EXTENSIONS = {"pdf", "doc"};

    private String basePath = PropertyUtil.loadProperty("basePath");

    public File retrieveFile(String fileName) {
        validateFileType(fileName);
        final String dirPath = basePath + File.separator;
        return Paths.get(dirPath, fileName).toFile();
    }

    public List<String> findImagesByExtensions() {
        return findFilesInDirectoryByExtensions(basePath, IMAGE_VALID_EXTENSIONS);
    }

    public List<String> findDocumentFilesByExtensions() {
        return findFilesInDirectoryByExtensions(basePath, FILE_VALID_EXTENSIONS);
    }

    private void validateFileType(String fileName) {
        if (isInvalidFileType(fileName)) {
            throw new InvalidFileTypeException("File type not Supported: " + fileName);
        }
    }

    private boolean isInvalidFileType(String fileName) {
        return isInvalidImage(fileName) && isInvalidDocument(fileName);
    }

    private boolean isInvalidImage(String fileName) {
        FileExtensionsPredicate imageExtensionsPredicate = new FileExtensionsPredicate(IMAGE_VALID_EXTENSIONS);
        return !imageExtensionsPredicate.test(fileName);
    }

    private boolean isInvalidDocument(String fileName) {
        FileExtensionsPredicate documentExtensionsPredicate = new FileExtensionsPredicate(FILE_VALID_EXTENSIONS);
        return !documentExtensionsPredicate.test(fileName);
    }

    private List<String> findFilesInDirectoryByExtensions(String directoryPath, String[] allowedExtensions) {
        final FileExtensionsPredicate fileExtend = new FileExtensionsPredicate(allowedExtensions);
        return Arrays.asList(findDirectory(directoryPath).list(getFileNameFilterByPredicate(fileExtend)));
    }

    private FilenameFilter getFileNameFilterByPredicate(final FileExtensionsPredicate pred) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File fileDirectory, String fileName) {
                return pred.test(fileName);
            }
        };
    }

    private File findDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        validateDirectory(directory);
        return directory;
    }

    private void validateDirectory(File directory) {
        if (isNotDirectory(directory)) {
            throw new InvalidDirectoryException("Invalid directory found: " + directory.getAbsolutePath());
        }
    }

    private boolean isNotDirectory(File directory) {
        return !directory.isDirectory();
    }

}