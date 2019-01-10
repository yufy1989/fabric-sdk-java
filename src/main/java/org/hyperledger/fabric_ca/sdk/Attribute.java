/*
 *  Copyright 2017 DTCC, Fujitsu Australia Software Technology, IBM - All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.hyperledger.fabric_ca.sdk;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

// An attribute name and value which is used when registering a new user
public class Attribute {
    //add by yfy 2019年1月10日22:36:02
    // 当set了adminUserContext的HFCAClient调用register(RegistrationRequest request, User registrar)方法时，需要传入该对象，
    // 该对象对应了users表中的attributes列，规定了若干属性如下所示
    /**
     * hf.Registrar.Roles : List     注册者可以管理的角色列表

     hf.Registrar.DelegateRoles : List      注册者可以授予被注册身份hf.Registrar.Roles属性的角色列表

     hf.Registrar.Attributes : List       注册者允许注册的属性列表

     hf.GenCRL : Boolean   如果true则身份可以生成CRL

     hf.Revoker : Boolean    如果true则身份可以撤销用户或证书

     hf.AffiliationMgr : Boolean   如果true则身份可以管理组织关系

     hf.IntermediateCA : Boolean     如果true则身份可以登记中间CA Server
     ============================以上这些属性表示的是用户是否拥有相关的权限 =====================================================
     hf.EnrollmentID ： 该用户名称
     hf.Type ： 该用户类型
     hf.Affiliation ： 该用户隶属于那个组织
     */

    private final Boolean ecert;
    private String name;
    private String value;

    public Attribute(String name, String value) {
        this(name, value, null);
    }

    /**
     * @param name             Attribute name.
     * @param value            Attribute value.
     * @param defaultAttribute Attribute should be included in certificate even if not specified during enrollment.
     */
    public Attribute(String name, String value, Boolean defaultAttribute) {
        this.name = name;
        this.value = value;
        this.ecert = defaultAttribute;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public JsonObject toJsonObject() {
        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("name", name);
        ob.add("value", value);
        if (ecert != null) {
            ob.add("ecert", ecert.booleanValue());
        }
        return ob.build();
    }

}
