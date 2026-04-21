

# Activite


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **UUID** |  |  |
|**titre** | **String** |  |  |
|**type** | **TypeActiviteEnum** |  |  |
|**date** | **OffsetDateTime** |  |  |
|**lieu** | **String** |  |  [optional] |
|**presenceObligatoire** | **Boolean** |  |  |
|**membresConvies** | **List&lt;UUID&gt;** | Vide &#x3D; tous les membres ; sinon liste des membres concernés |  [optional] |
|**collectiviteId** | **UUID** | Null si activité de la fédération |  [optional] |



