package bucket;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.B2Bucket;
import com.backblaze.b2.client.structures.B2DownloadAuthorization;
import com.backblaze.b2.client.structures.B2GetDownloadAuthorizationRequest;

public class B2Utils {

            // Backblaze credentials
            private static String keyId = ""; // Replace with your Key ID
            private static String applicationKey = ""; // Replace with your Application Key
            private static String bucketName = ""; // Replace with your bucket name

            // Initialize B2 client
            public static B2StorageClient intializeB2Client() throws B2Exception{

                B2StorageClient b2Client = null;

                System.out.println("Authenticating with Backblaze B2...");
                b2Client = B2StorageClientFactory.createDefaultFactory()
                                           .create(keyId, applicationKey, "B2UploaderApp");
                System.out.println("Authentication Successful!");

                return b2Client;
            }
            
            // Get the bucket by name
            public static B2Bucket getBucket() throws B2Exception{
                System.out.println("Getting bucket by name...");
                B2StorageClient b2Client = intializeB2Client();
                B2Bucket bucket = b2Client.getBucketOrNullByName(bucketName);
                if (bucket == null) {
                    System.err.println("Bucket not found!");
                }
                System.out.println("Bucket found: " + bucket.getBucketName());
                return bucket;
            }
            
            // Generate a download authorization token (valid for 1 hour)
            private static B2DownloadAuthorization downloadAuthorizationToken(String fileName) throws B2Exception{
                System.out.println("Generating download authorization token...");
                B2DownloadAuthorization downloadAuthorizationToken = intializeB2Client().getDownloadAuthorization(
                    B2GetDownloadAuthorizationRequest.builder(getBucket().getBucketId(), fileName, 3600).build());
                System.out.println("Download authorization token generated!");
                return downloadAuthorizationToken;
            }

             // Construct the pre-signed URL
            public static String contructPreSignedURL(String fileName) throws B2Exception{ 
                
                System.out.println("Constructing pre-signed URL...");
                String fileUrl = String.format("https://f000.backblazeb2.com/file/%s/%s?Authorization=%s",
                       bucketName, fileName, downloadAuthorizationToken(fileName).getAuthorizationToken());
                System.out.println("Public Download URL: " + fileUrl);
                return fileUrl;
            }
            
            

}
