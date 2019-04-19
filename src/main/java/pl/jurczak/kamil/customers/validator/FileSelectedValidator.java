package pl.jurczak.kamil.customers.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class FileSelectedValidator implements ConstraintValidator<IsFileSelected, MultipartFile> {

    @Override
    public void initialize(IsFileSelected isFileSelected) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return isFileSelected(multipartFile);
    }

    private boolean isFileSelected(MultipartFile multipartFile) {
        return multipartFile != null && !multipartFile.isEmpty();
    }
}
