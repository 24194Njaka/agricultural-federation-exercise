# MembresApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**admettreMembreCollectivite**](MembresApi.md#admettreMembreCollectivite) | **POST** /collectivites/{collectiviteId}/membres | Admettre un nouveau membre dans une collectivité |
| [**createMandat**](MembresApi.md#createMandat) | **POST** /collectivites/{collectiviteId}/mandats | Enregistrer un nouveau mandat (résultat de vote) |
| [**demissionMembre**](MembresApi.md#demissionMembre) | **POST** /collectivites/{collectiviteId}/membres/{membreId}/demission | Enregistrer la démission d&#39;un membre |
| [**getMembreCollectivite**](MembresApi.md#getMembreCollectivite) | **GET** /collectivites/{collectiviteId}/membres/{membreId} | Obtenir les détails d&#39;un membre dans une collectivité |
| [**listMandats**](MembresApi.md#listMandats) | **GET** /collectivites/{collectiviteId}/mandats | Lister les mandats d&#39;une collectivité |
| [**listMembresCollectivite**](MembresApi.md#listMembresCollectivite) | **GET** /collectivites/{collectiviteId}/membres | Lister les membres d&#39;une collectivité |
| [**transfererMembre**](MembresApi.md#transfererMembre) | **POST** /collectivites/{collectiviteId}/membres/{membreId}/transfert | Transférer un membre vers une autre collectivité |
| [**updateMembreCollectivite**](MembresApi.md#updateMembreCollectivite) | **PUT** /collectivites/{collectiviteId}/membres/{membreId} | Mettre à jour les informations d&#39;un membre |


<a id="admettreMembreCollectivite"></a>
# **admettreMembreCollectivite**
> Membre admettreMembreCollectivite(collectiviteId, admissionMembreRequest)

Admettre un nouveau membre dans une collectivité

Conditions d&#39;admission (B-2 mis à jour) : - Être parrainé par au moins 2 membres confirmés ; le nombre de parrains   issus de la collectivité cible doit être ≥ nombre de parrains issus d&#39;autres collectivités. - Fournir ses informations personnelles (dont la relation avec chaque parrain). - Régler les frais d&#39;adhésion (50 000 MGA) + cotisations annuelles obligatoires   imposées par la collectivité. 

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    AdmissionMembreRequest admissionMembreRequest = new AdmissionMembreRequest(); // AdmissionMembreRequest | 
    try {
      Membre result = apiInstance.admettreMembreCollectivite(collectiviteId, admissionMembreRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#admettreMembreCollectivite");
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
| **admissionMembreRequest** | [**AdmissionMembreRequest**](AdmissionMembreRequest.md)|  | |

### Return type

[**Membre**](Membre.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Membre admis avec succès |  -  |
| **400** |  |  -  |
| **422** |  |  -  |

<a id="createMandat"></a>
# **createMandat**
> Mandat createMandat(collectiviteId, mandatRequest)

Enregistrer un nouveau mandat (résultat de vote)

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    MandatRequest mandatRequest = new MandatRequest(); // MandatRequest | 
    try {
      Mandat result = apiInstance.createMandat(collectiviteId, mandatRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#createMandat");
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
| **mandatRequest** | [**MandatRequest**](MandatRequest.md)|  | |

### Return type

[**Mandat**](Mandat.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Mandat créé |  -  |
| **400** |  |  -  |
| **422** |  |  -  |

<a id="demissionMembre"></a>
# **demissionMembre**
> demissionMembre(collectiviteId, membreId, demissionRequest)

Enregistrer la démission d&#39;un membre

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID membreId = UUID.randomUUID(); // UUID | Identifiant unique du membre
    DemissionRequest demissionRequest = new DemissionRequest(); // DemissionRequest | 
    try {
      apiInstance.demissionMembre(collectiviteId, membreId, demissionRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#demissionMembre");
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
| **membreId** | **UUID**| Identifiant unique du membre | |
| **demissionRequest** | [**DemissionRequest**](DemissionRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Démission enregistrée |  -  |
| **404** |  |  -  |

<a id="getMembreCollectivite"></a>
# **getMembreCollectivite**
> Membre getMembreCollectivite(collectiviteId, membreId)

Obtenir les détails d&#39;un membre dans une collectivité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID membreId = UUID.randomUUID(); // UUID | Identifiant unique du membre
    try {
      Membre result = apiInstance.getMembreCollectivite(collectiviteId, membreId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#getMembreCollectivite");
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
| **membreId** | **UUID**| Identifiant unique du membre | |

### Return type

[**Membre**](Membre.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Détails du membre |  -  |
| **404** |  |  -  |

<a id="listMandats"></a>
# **listMandats**
> List&lt;Mandat&gt; listMandats(collectiviteId, annee)

Lister les mandats d&#39;une collectivité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    Integer annee = 56; // Integer | Filtrer par année civile
    try {
      List<Mandat> result = apiInstance.listMandats(collectiviteId, annee);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#listMandats");
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
| **annee** | **Integer**| Filtrer par année civile | [optional] |

### Return type

[**List&lt;Mandat&gt;**](Mandat.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des mandats |  -  |
| **404** |  |  -  |

<a id="listMembresCollectivite"></a>
# **listMembresCollectivite**
> MembrePage listMembresCollectivite(collectiviteId, page, size, poste, actif)

Lister les membres d&#39;une collectivité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    Integer page = 0; // Integer | 
    Integer size = 20; // Integer | 
    PosteEnum poste = PosteEnum.fromValue("PRESIDENT"); // PosteEnum | Filtrer par poste
    Boolean actif = true; // Boolean | Filtrer les membres actifs (non démissionnaires)
    try {
      MembrePage result = apiInstance.listMembresCollectivite(collectiviteId, page, size, poste, actif);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#listMembresCollectivite");
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
| **poste** | [**PosteEnum**](.md)| Filtrer par poste | [optional] [enum: PRESIDENT, PRESIDENT_ADJOINT, TRESORIER, SECRETAIRE, MEMBRE_CONFIRME, MEMBRE_JUNIOR] |
| **actif** | **Boolean**| Filtrer les membres actifs (non démissionnaires) | [optional] |

### Return type

[**MembrePage**](MembrePage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Liste des membres |  -  |
| **404** |  |  -  |

<a id="transfererMembre"></a>
# **transfererMembre**
> Membre transfererMembre(collectiviteId, membreId, transfertMembreRequest)

Transférer un membre vers une autre collectivité

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID membreId = UUID.randomUUID(); // UUID | Identifiant unique du membre
    TransfertMembreRequest transfertMembreRequest = new TransfertMembreRequest(); // TransfertMembreRequest | 
    try {
      Membre result = apiInstance.transfererMembre(collectiviteId, membreId, transfertMembreRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#transfererMembre");
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
| **membreId** | **UUID**| Identifiant unique du membre | |
| **transfertMembreRequest** | [**TransfertMembreRequest**](TransfertMembreRequest.md)|  | |

### Return type

[**Membre**](Membre.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Transfert effectué |  -  |
| **404** |  |  -  |
| **422** |  |  -  |

<a id="updateMembreCollectivite"></a>
# **updateMembreCollectivite**
> Membre updateMembreCollectivite(collectiviteId, membreId, membreUpdateRequest)

Mettre à jour les informations d&#39;un membre

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
    UUID collectiviteId = UUID.randomUUID(); // UUID | Identifiant unique de la collectivité
    UUID membreId = UUID.randomUUID(); // UUID | Identifiant unique du membre
    MembreUpdateRequest membreUpdateRequest = new MembreUpdateRequest(); // MembreUpdateRequest | 
    try {
      Membre result = apiInstance.updateMembreCollectivite(collectiviteId, membreId, membreUpdateRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling MembresApi#updateMembreCollectivite");
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
| **membreId** | **UUID**| Identifiant unique du membre | |
| **membreUpdateRequest** | [**MembreUpdateRequest**](MembreUpdateRequest.md)|  | |

### Return type

[**Membre**](Membre.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Membre mis à jour |  -  |
| **404** |  |  -  |

