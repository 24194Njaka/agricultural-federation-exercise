# MembresApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**collectivitesCollectiviteIdMembresPost**](MembresApi.md#collectivitesCollectiviteIdMembresPost) | **POST** /collectivites/{collectiviteId}/membres | Admettre un nouveau membre dans une collectivité (Fonctionnalité B) |


<a id="collectivitesCollectiviteIdMembresPost"></a>
# **collectivitesCollectiviteIdMembresPost**
> Membre collectivitesCollectiviteIdMembresPost(collectiviteId, membreAdmission)

Admettre un nouveau membre dans une collectivité (Fonctionnalité B)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.MembresApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    MembresApi apiInstance = new MembresApi(defaultClient);
    Long collectiviteId = 56L; // Long | 
    MembreAdmission membreAdmission = new MembreAdmission(); // MembreAdmission | 
    try {
      Membre result = apiInstance.collectivitesCollectiviteIdMembresPost(collectiviteId, membreAdmission);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#collectivitesCollectiviteIdMembresPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **collectiviteId** | **Long**|  | |
| **membreAdmission** | [**MembreAdmission**](MembreAdmission.md)|  | |

### Return type

[**Membre**](Membre.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json, text/plain

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Membre admis avec succès |  -  |
| **400** | Conditions non remplies (parrainage, frais, etc.) |  -  |

