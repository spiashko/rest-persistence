

### TODO
- integrate with security (make it depended on JsonView)
- try to apply JSON:API concept
- add swagger integration
- add setting null to fields which didn't mean to be loaded 
  - may be possible to resolve via own Hibernate like proxy and additional field/post load wrap in proxy and jackson module
  - may be just jackson module (custom serializer which write null into json despite it is not null for object)

### implementation thoughts:
 - JSON:API concept should be applied if we are going to include relations but if we are going to include on level of
aggregation root then there is no need in JSON:API concept 
 - BUT looks like it will be easier to create that JSON:API wrapper anyway

### usage thoughts
 - as this something that configured on each separate endpoint then it means that for some tricky case we can always
write endpoint in our usual way with dto and other boring things

make thruogh serialazer modifier

