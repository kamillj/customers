package pl.jurczak.kamil.customers.validator;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.jurczak.kamil.customers.enumeration.FileExtentions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class FileTypeValidator implements ConstraintValidator<SupportedFileType, MultipartFile> {

    @Override
    public void initialize(SupportedFileType supportedFileType) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return isFileTypeSupported(multipartFile);
    }

    private boolean isFileTypeSupported(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) return true;
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename()).toUpperCase();
        return FileExtentions.getValues().contains(extension);
    }


//public class FileValidator implements Validator {

//    private static final List<String> EXTENSIONS = Arrays.asList("csv", "txt", "xml");
//
//    @Override
//        public boolean supports(Class<?> clazz) {
//        return FileBucket.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        FileBucket bucket = new FileBucket();
//        bucket.setFile((MultipartFile) target);
//        if (bucket.getFile() != null && bucket.getFile().isEmpty()){
//            errors.rejectValue("file", "file.empty");
//        } else if (!EXTENSIONS.contains(FilenameUtils.getExtension(bucket.getFile().getOriginalFilename()))){
//            errors.rejectValue("file", "file.wrong");
//        }
//    }
}
