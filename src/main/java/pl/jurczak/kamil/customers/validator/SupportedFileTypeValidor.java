package pl.jurczak.kamil.customers.validator;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.jurczak.kamil.customers.enumeration.FileExtension;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class SupportedFileTypeValidor implements ConstraintValidator<SupportedFileType, MultipartFile> {

    @Override
    public void initialize(SupportedFileType supportedFileType) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return isFileSupported(multipartFile);
    }

    private boolean isFileSupported(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) return true;
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename()).toUpperCase();
        return FileExtension.getValues().contains(extension);
    }
}
