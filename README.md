## Spring Dinamic Job Scheduling using Quartz
  
###  Run Application
```bash
> mvnw clean spring-boot:run
```

### Features
**CREATE**  
Method      : `POST: /groups/:group/jobs`  
Status      : `201: Created`  
Body        :
```json
{
  "name": "hello",
  "content": "Hello World!",
  "triggers":
    [
       {
         "name": "hello",
         "group": "message",
         "fireTime": "2018-02-02T08:00:00.000"
       }
    ]
}
```
Content-Type: `application/json`

**VIEW**  
Method      : `GET: /groups/:group/jobs/:name`  
Status      : `200: Ok`  
Body        : NULL  
Accept      : `application/json`

**UPDATE**  
Method      : `PUT: /groups/:group/jobs/:name`  
Status      : `204: No Content`  
Body        :
```json
{
  "name": "message",
  "content": "Hello Spring and Quartz!"
}
```
Content-Type: `application/json`

**UPDATE (Pause)**  
Method      : `PATCH: /groups/:group/jobs/:name/pause`  
Status      : `204: No Content`  
Body        : NULL  
Content-Type: `*/*`

**UPDATE (Resume)**  
Method      : `PATCH: /groups/:group/jobs/:name/resume`  
Status      : `204: No Content`  
Body        : NULL  
Content-Type: `*/*`

**DELETE**  
Method      : `DELETE: /groups/:group/jobs/:name`  
Status      : `204: No Content`  
Body        : NULL  
Content-Type: `*/*`

For more detail, please refer to this link tutorial : http://juliuskrah.com/tutorial/2017/09/26/dynamic-job-scheduling-with-quartz-and-spring/ 