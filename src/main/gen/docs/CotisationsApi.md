# CotisationsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createCotisation**](CotisationsApi.md#createCotisation) | **POST** /collectivites/{collectiviteId}/cotisations | Créer une cotisation (périodique ou ponctuelle) |
| [**createPaiement**](CotisationsApi.md#createPaiement) | **POST** /collectivites/{collectiviteId}/paiements | Enregistrer un encaissement (paiement d&#39;un membre) |
| [**listCotisations**](CotisationsApi.md#listCotisations) | **GET** /collectivites/{collectiviteId}/cotisations | Lister les cotisations d&#39;une collectivité |
| [**listPaiements**](CotisationsApi.md#listPaiements) | **GET** /collectivites/{collectiviteId}/paiements | Lister les encaissements d&#39;une collectivité |


<a id="createCotisation"></a>
# **createCotisation**
> Cotisation createCotisation(collectiviteId, cotisationRequest)

Créer une cotisation (périodique ou ponctuelle)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CotisationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    CotisationsApi apiInstance = new CotisationsApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    CotisationRequest cotisationRequest = new CotisationRequest(); // CotisationRequest | 
    try {
      Cotisation result = apiInstance.createCotisation(collectiviteId, cotisationRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CotisationsApi#createCotisation");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **cotisationRequest** | [**CotisationRequest**](CotisationRequest.md)|  | |

### Return type

[**Cotisation**](Cotisation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Cotisation créée |  -  |
| **404** |  |  -  |

<a id="createPaiement"></a>
# **createPaiement**
> Paiement createPaiement(collectiviteId, paiementRequest)

Enregistrer un encaissement (paiement d&#39;un membre)

Le trésorier enregistre le montant, la date et le mode de paiement.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CotisationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    CotisationsApi apiInstance = new CotisationsApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    PaiementRequest paiementRequest = new PaiementRequest(); // PaiementRequest | 
    try {
      Paiement result = apiInstance.createPaiement(collectiviteId, paiementRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CotisationsApi#createPaiement");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **paiementRequest** | [**PaiementRequest**](PaiementRequest.md)|  | |

### Return type

[**Paiement**](Paiement.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Encaissement enregistré |  -  |
| **404** |  |  -  |
| **422** |  |  -  |

<a id="listCotisations"></a>
# **listCotisations**
> CotisationPage listCotisations(collectiviteId, page, size, type, dateDebut, dateFin)

Lister les cotisations d&#39;une collectivité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CotisationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    CotisationsApi apiInstance = new CotisationsApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    Integer page = 0; // Integer | 
    Integer size = 20; // Integer | 
    TypeCotisationEnum type = TypeCotisationEnum.fromValue("MENSUELLE"); // TypeCotisationEnum | 
    LocalDate dateDebut = LocalDate.now(); // LocalDate | 
    LocalDate dateFin = LocalDate.now(); // LocalDate | 
    try {
      CotisationPage result = apiInstance.listCotisations(collectiviteId, page, size, type, dateDebut, dateFin);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CotisationsApi#listCotisations");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 20] |
| **type** | [**TypeCotisationEnum**](.md)|  | [optional] [enum: MENSUELLE, ANNUELLE, PONCTUELLE] |
| **dateDebut** | **LocalDate**|  | [optional] |
| **dateFin** | **LocalDate**|  | [optional] |

### Return type

[**CotisationPage**](CotisationPage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des cotisations |  -  |
| **404** |  |  -  |

<a id="listPaiements"></a>
# **listPaiements**
> PaiementPage listPaiements(collectiviteId, page, size, membreId, dateDebut, dateFin, modePaiement)

Lister les encaissements d&#39;une collectivité

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.CotisationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    CotisationsApi apiInstance = new CotisationsApi(defaultClient);
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    Integer page = 0; // Integer | 
    Integer size = 20; // Integer | 
    UUID membreId = UUID.randomUUID(); // UUID | 
    LocalDate dateDebut = LocalDate.now(); // LocalDate | 
    LocalDate dateFin = LocalDate.now(); // LocalDate | 
    ModePaiementEnum modePaiement = ModePaiementEnum.fromValue("ESPECE"); // ModePaiementEnum | 
    try {
      PaiementPage result = apiInstance.listPaiements(collectiviteId, page, size, membreId, dateDebut, dateFin, modePaiement);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling CotisationsApi#listPaiements");
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
| **collectiviteId** | **UUID**| Identifiant unique de la collectivité | |
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 20] |
| **membreId** | **UUID**|  | [optional] |
| **dateDebut** | **LocalDate**|  | [optional] |
| **dateFin** | **LocalDate**|  | [optional] |
| **modePaiement** | [**ModePaiementEnum**](.md)|  | [optional] [enum: ESPECE, VIREMENT_BANCAIRE, MOBILE_MONEY] |

### Return type

[**PaiementPage**](PaiementPage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des encaissements |  -  |
| **404** |  |  -  |

