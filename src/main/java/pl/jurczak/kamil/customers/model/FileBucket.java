package pl.jurczak.kamil.customers.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import pl.jurczak.kamil.customers.validator.IsFileSelected;
import pl.jurczak.kamil.customers.validator.SupportedFileType;

@Data
public class FileBucket {

    @IsFileSelected
    @SupportedFileType
    private MultipartFile file;
}
