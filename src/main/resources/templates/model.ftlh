package ${config.modelPackage};

import javax.persistence.*;
<#list table.packages as p>
import ${p};
</#list>


@Table(name = "${table.tableName}")
public class ${config.modelName} {

     <#list table.columns as d>
     //${d.remarks!}
     <#if d.primaryKey>
     @Id
     </#if>
     @Column(name = "${d.columnName}")
     private ${d.propertyType} ${d.propertyName};
     
     </#list>
     
     <#list table.columns as d>
     public ${d.propertyType} get${d.propertyName?cap_first}() {
        return ${d.propertyName};
     }

     public void set${d.propertyName?cap_first}(${d.propertyType} ${d.propertyName}) {
        this.${d.propertyName} = ${d.propertyName};
     }
     
     </#list>
     
}