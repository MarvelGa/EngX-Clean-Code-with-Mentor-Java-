package com.epam.engx.cleancode.naming.task5;

import com.epam.engx.cleancode.naming.task5.thirdpartyjar.InvalidFileTypeException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileManagerTest {

    FileManager fileManager = new FileManager();

    @Test
    public void should_listAllImageFiles() {
        List<String> images = fileManager.findImagesByExtensions();
        Assert.assertNotNull(images);
        Assert.assertEquals(images.size(), 1);
        Assert.assertEquals(images.get(0), "epam.png");
    }

    @Test
    public void should_listAllDocumentFiles() {
        List<String> documents = fileManager.findDocumentFilesByExtensions();
        Assert.assertNotNull(documents);
        Assert.assertEquals(documents.size(), 1);
        Assert.assertEquals(documents.get(0), "sample.doc");
    }

    @Test
    public void should_retrieveFile_when_validImage() {
        File image = fileManager.retrieveFile("epam.png");
        Assert.assertTrue(image.exists());
    }

    @Test(expected = InvalidFileTypeException.class)
    public void should_throwException_when_unsupportedImageType() {
        fileManager.retrieveFile("invalidImage.img");
    }

    @Test
    public void should_returnEmpty_when_noImageExists() {
        File image = fileManager.retrieveFile("invalidImage.jpg");
        Assert.assertFalse(image.exists());
    }

    @Test
    public void should_retrieveFile_when_validDocument() {
        File document = fileManager.retrieveFile("sample.doc");
        Assert.assertTrue(document.exists());
    }

    @Test(expected = InvalidFileTypeException.class)
    public void should_throwException_when_unsupportedDocumentType() {
        fileManager.retrieveFile("invalidDoc.java");
    }

    @Test(expected = InvalidFileTypeException.class)
    public void should_throwException_when_retrieveFileWithNoExtension() {
        fileManager.retrieveFile("noExtension");
    }

    @Test
    public void should_returnEmpty_when_noDocumentExists() {
        File document = fileManager.retrieveFile("invalidDoc.pdf");
        Assert.assertFalse(document.exists());
    }
}
