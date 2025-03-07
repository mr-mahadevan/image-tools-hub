package bucket;

import java.io.File;
import java.io.IOException;
import com.backblaze.b2.client.contentSources.B2FileContentSource;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.B2UploadFileRequest;

public class StoreImage {

    public void storeImage(String inputImagePath, String imageType) throws B2Exception, IOException{
       
        // Specify the file to upload
            File file = new File(inputImagePath);
            System.out.println("Preparing file: " + file.getName());
            B2FileContentSource source = B2FileContentSource.build(file);
            System.out.println("File prepared: " + file.getName());


            // Upload request
            System.out.println("Loading file for upload: " + file.getName());

            B2UploadFileRequest uploadRequest = B2UploadFileRequest
                    .builder(B2Utils.getBucket().getBucketId(), file.getName(), "Image", source)
                    .build();
            
            System.out.println("File loaded for upload: " + file.getName());

            System.out.println("Uplaoding file: " + file.getName());
            // Upload file
            B2Utils.intializeB2Client().uploadSmallFile(uploadRequest);
            System.out.println("File uploaded successfully: " + file.getName());

    }

   
}
