# TrsorerieApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**collectivitesCollectiviteIdComptesGet**](TrsorerieApi.md#collectivitesCollectiviteIdComptesGet) | **GET** /collectivites/{collectiviteId}/comptes | Lister tous les comptes d’une collectivité (Fonctionnalité D) |
| [**comptesCompteIdSoldeGet**](TrsorerieApi.md#comptesCompteIdSoldeGet) | **GET** /comptes/{compteId}/solde | Consulter le solde d’un compte à une date donnée (Fonctionnalité D) |
| [**encaissementsPost**](TrsorerieApi.md#encaissementsPost) | **POST** /encaissements | Enregistrer un encaissement / cotisation (Fonctionnalité C) |


<a id="collectivitesCollectiviteIdComptesGet"></a>
# **collectivitesCollectiviteIdComptesGet**
> List&lt;Compte&gt; collectivitesCollectiviteIdComptesGet(collectiviteId)

Lister tous les comptes d’une collectivité (Fonctionnalité D)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    Long collectiviteId = 56L; // Long | 
    try {
      List<Compte> result = apiInstance.collectivitesCollectiviteIdComptesGet(collectiviteId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#collectivitesCollectiviteIdComptesGet");
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

### Return type

[**List&lt;Compte&gt;**](Compte.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des comptes et soldes |  -  |

<a id="comptesCompteIdSoldeGet"></a>
# **comptesCompteIdSoldeGet**
> Solde comptesCompteIdSoldeGet(compteId, at)

Consulter le solde d’un compte à une date donnée (Fonctionnalité D)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    Long compteId = 56L; // Long | 
    LocalDate at = LocalDate.now(); // LocalDate | 
    try {
      Solde result = apiInstance.comptesCompteIdSoldeGet(compteId, at);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#comptesCompteIdSoldeGet");
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
| **compteId** | **Long**|  | |
| **at** | **LocalDate**|  | |

### Return type

[**Solde**](Solde.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Solde du compte |  -  |

<a id="encaissementsPost"></a>
# **encaissementsPost**
> Encaissement encaissementsPost(encaissement)

Enregistrer un encaissement / cotisation (Fonctionnalité C)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.TrsorerieApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    TrsorerieApi apiInstance = new TrsorerieApi(defaultClient);
    Encaissement encaissement = new Encaissement(); // Encaissement | 
    try {
      Encaissement result = apiInstance.encaissementsPost(encaissement);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling TrsorerieApi#encaissementsPost");
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
| **encaissement** | [**Encaissement**](Encaissement.md)|  | |

### Return type

[**Encaissement**](Encaissement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Encaissement enregistré |  -  |

