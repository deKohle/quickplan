# QUICKPLAN

**NOTE: This project was created for DevOps and is not affiliated to CloudComputing or MobileApplications**

## Description

This project is a Web-Application for appointments. You can do the basic CRUD actions either by accessing the main page 
through your browser or by accessing the API.

## API

### ```/appointment```

#### ```POST``` (CREATE)

can only create new appointments

##### Parameters

> | name          |  type                                            | data type                                       | description                                        |
> |---------------|--------------------------------------------------|-------------------------------------------------|----------------------------------------------------|
> | date          |  required (if end and begin are not specified)   | string (yyyy-mm-dd)                             | use if the appointment lasts for this single day   |
> | begin         |  required (if date is not specified)             | string (yyyy-\[m]m-\[d]d hh:mm\[:ss\[.f...]])   | the time the appointment begins   |
> | end           |  required (if date is not specified)             | string (yyyy-\[m]m-\[d]d hh:mm\[:ss\[.f...]])   | the time the appointment ends   |
> | description   |  required                                        | string                                          | informations about the appointment   |

##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `text/html;charset=UTF-8`         | ```HTML```-fragment that can be displayed on a website              |
> | `400`         | `text/html;charset=UTF-8`         | None                                                                |

#### ```GET``` (READ)

get one or multiple appointments

##### Parameters

> | name          |  type                                            | data type                                       | description                                        |
> |---------------|--------------------------------------------------|-------------------------------------------------|----------------------------------------------------|
> | identifier    |  required (if end and begin are not specified)   | string (UUID)                                   | use if you want a specific appointment   |
> | begin         |  required (if identifier is not specified)       | string (yyyy-\[m]m-\[d]d hh:mm\[:ss\[.f...]])   | the begin for the appointment-search   |
> | end           |  required (if identifier is not specified)       | string (yyyy-\[m]m-\[d]d hh:mm\[:ss\[.f...]])   | the end for the appointment-search   |

##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json`                | ```JSON```-representation of the found objects                      |
> | `400`         | `text/html;charset=UTF-8`         | None                                                                |

#### ```PUT``` (UPDATE)

update an existing appointment

##### Parameters

> | name          |  type                                            | data type                                       | description                                        |
> |---------------|--------------------------------------------------|-------------------------------------------------|----------------------------------------------------|
> | identifier    |  required                                        | string (UUID)                                   | updates this given appointment   |
> | date          |  required (if end and begin are not specified)   | string (yyyy-mm-dd)                             | use if the appointment lasts for this single day   |
> | begin         |  required (if date is not specified)             | string (yyyy-\[m]m-\[d]d hh:mm\[:ss\[.f...]])   | the time the appointment begins   |
> | end           |  required (if date is not specified)             | string (yyyy-\[m]m-\[d]d hh:mm\[:ss\[.f...]])   | the time the appointment ends   |
> | description   |  required                                        | string                                          | informations about the appointment   |

##### Responses

> | http code     | content-type                      | response                                                            |
> |---------------|-----------------------------------|---------------------------------------------------------------------|
> | `200`         | `application/json`                | ```JSON```-representation of the updated object                     |
> | `400`         | `text/html;charset=UTF-8`         | None                                                                |

#### ```DELETE``` (DELETE)

delete an existing appointment

##### Parameters

> | name          |  type                                            | data type                                       | description                                        |
> |---------------|--------------------------------------------------|-------------------------------------------------|----------------------------------------------------|
> | identifier    |  required                                        | string (UUID)                                   | the ID of the appointment   |

##### Responses

> | http code     | content-type                      | response                 |
> |---------------|-----------------------------------|--------------------------|
> | `200`         | `text/html;charset=UTF-8`         | None                     |
> | `400`         | `text/html;charset=UTF-8`         | None                     |