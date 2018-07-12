package com.tangxigua.yunxiao.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tangxigua.yunxiao.model.Commodity;
import com.tangxigua.yunxiao.model.CustomerCategoryEnum;
import com.tangxigua.yunxiao.util.ElasticsearchUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class ElasticsearchServiceImpl implements ElasticsearchService{

    @Value("${spring.elasticsearch.host}")
    private String host;

    @Value("${spring.elasticsearch.port}")
    private Integer port;

    @Value("${spring.elasticsearch.index}")
    private String index;

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    @PostConstruct
    void initElasticsearchUtils(){
        String[] hostStr = host.split(",");
        HttpHost[] hosts = new HttpHost[hostStr.length];
        for(int i =0;i< hostStr.length;i++){
            hosts[i] = new HttpHost(hostStr[i], port);
        }
        ElasticsearchUtils.init(username, password, hosts);
    }

    @Override
    public boolean update(Long id, CustomerCategoryEnum customerCategoryEnum) {
        return false;
    }

    @Override
    public boolean delete(Long id, CustomerCategoryEnum customerCategoryEnum) {
        return false;
    }

    @Override
    public List<Commodity> queryCommodity(String keyWord) {

        try {
        Response response = ElasticsearchUtils.getRestClient().performRequest("GET", "/", Collections.singletonMap("pretty", "true"));
        System.out.println(EntityUtils.toString(response.getEntity()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean refresh() {
        return false;
    }


    //    @Override
//    public boolean index(Long id, CustomerCategoryEnum customerCategoryEnum){
//        boolean res = false;
//        String tmpTypes = "";
//        String entity ="{}";
//        switch (customerCategoryEnum) {
//            case AGENT:
//                entity = esDataUtil.fromAgentVo(agentService.getAgentVOById(id)).toJSONString();
//                tmpTypes = AGENT_TYPE;
//                break;
//            case ADVERTISER:
//                entity = esDataUtil.fromAdvertiserVo(advertiserService.getAdvertiserVOById(id)).toJSONString();
//                tmpTypes = ADVERTISER_TYPE;
//                break;
//            case PRODUCT_LINE:
//                entity = esDataUtil.fromProductLineVo(productLineService.getProductLineVOById(id)).toJSONString();
//                tmpTypes = PRODUCTLINE_TYPE;
//                break;
//        }
//        try {
//            res = ElasticsearchUtils.index(index, tmpTypes, id.toString(), entity);
//        }catch (IOException ex){
//            CrmAlertUtils.sendEsAlert("Elasticsearch index fail, Exception:"+ex.getMessage(), 1,1,1);
//            Log.crm.getLogger().error("[Elasticsearch Exception]",ex);
//        }
//        return res;
//    }
//
//    @Override
//    public boolean update(Long id, CustomerCategoryEnum customerCategoryEnum){
//        boolean res = false;
//        JSONObject etity = new JSONObject();
//        etity.put("detect_noop", false);
//        String tmpTypes = "";
//        switch (customerCategoryEnum) {
//            case AGENT:
//                etity.put("doc", esDataUtil.fromAgentVo(agentService.getAgentVOById(id)));
//                tmpTypes = AGENT_TYPE;
//                break;
//            case ADVERTISER:
//                etity.put("doc", esDataUtil.fromAdvertiserVo(advertiserService.getAdvertiserVOById(id)));
//                tmpTypes = ADVERTISER_TYPE;
//                break;
//            case PRODUCT_LINE:
//                etity.put("doc", esDataUtil.fromProductLineVo(productLineService.getProductLineVOById(id)));
//                tmpTypes = PRODUCTLINE_TYPE;
//                break;
//        }
//        try {
//            res = ElasticsearchUtils.update(index, tmpTypes, id.toString(), etity.toJSONString());
//        } catch (IOException ex){
//            CrmAlertUtils.sendEsAlert("Elasticsearch update fail, Exception:"+ex.getMessage(), 1,1,1);
//            Log.crm.getLogger().error("[Elasticsearch Exception]",ex);
//        }
//        return res;
//    }
//
//    @Override
//    public boolean delete(Long id, CustomerCategoryEnum customerCategoryEnum){
//        boolean res = false;
//        try {
//            switch (customerCategoryEnum) {
//                case AGENT:
//                    res = ElasticsearchUtils.delete(index, AGENT_TYPE, id.toString());
//                    break;
//                case ADVERTISER:
//                    res = ElasticsearchUtils.delete(index, ADVERTISER_TYPE, id.toString());
//                    break;
//                case PRODUCT_LINE:
//                    res = ElasticsearchUtils.delete(index, PRODUCTLINE_TYPE, id.toString());
//                    break;
//            }
//        }catch (IOException ex){
//            CrmAlertUtils.sendEsAlert("Elasticsearch delete fail, Exception:"+ex.getMessage(), 1,1,1);
//            Log.crm.getLogger().error("[Elasticsearch Exception]",ex);
//        }catch (Exception e){
//            throw e;
//        }
//        return res;
//    }
//
//    @Override
//    public List<Commodity> queryCommodity(String keyWord) {
//        return null;
//    }
//
//    @Override
//    public Pagination<ProductLineListItemVO> queryProductLine(ProductLineSearchQuery searchQuery, String username) throws Exception{
//        try {
//            Pagination<ProductLineListItemVO> productLineListItemVOPagination = new Pagination<>();
//            productLineListItemVOPagination.setCurIndex(searchQuery.getCurPageIndex());
//            productLineListItemVOPagination.setPageSize(searchQuery.getPageSize());
//            JSONObject queryEntity = createQueryEntity(searchQuery, username);
//            if (searchQuery.getRegionId() != null) {
//                queryEntity.getJSONObject("query").getJSONObject("bool").getJSONArray("must").add(createQueryKv("term", "region.id", searchQuery.getRegionId()));
//            }
//            if (searchQuery.getCustomerType() != null) {
//                queryEntity.getJSONObject("query").getJSONObject("bool").getJSONArray("must").add(createQueryKv("term", "type", searchQuery.getCustomerType().getId()));
//            }
//            if (searchQuery.getIndustryIdLevel1() != null) {
//                queryEntity.getJSONObject("query").getJSONObject("bool").getJSONArray("must").add(createQueryKv("term", "industryLevel1.id", searchQuery.getIndustryIdLevel1()));
//            }
//            if (searchQuery.getIndustryIdLevel2() != null) {
//                queryEntity.getJSONObject("query").getJSONObject("bool").getJSONArray("must").add(createQueryKv("term", "industryLevel2.id", searchQuery.getIndustryIdLevel2()));
//            }
//            if (searchQuery.getIndustryIdLevel3() != null) {
//                queryEntity.getJSONObject("query").getJSONObject("bool").getJSONArray("must").add(createQueryKv("term", "industryLevel3.id", searchQuery.getIndustryIdLevel3()));
//            }
//            Log.crm.getLogger().info("[Elasticsearch searchQuery]{}",queryEntity.toJSONString());
//            JSONObject respone = JSONObject.parseObject(ElasticsearchUtils.search(index, PRODUCTLINE_TYPE, queryEntity.toJSONString()));
//            productLineListItemVOPagination.setRowsCount(respone.getJSONObject("hits").getLong("total"));
//            List<ProductLineListItemVO> productLineListItemVOList = new LinkedList<>();
//            for (JSONObject productLineEsData : respone.getJSONObject("hits").getJSONArray("hits").toJavaList(JSONObject.class)) {
//                ProductLineListItemVO productLineListItemVO = new ProductLineListItemVO();
//                productLineListItemVO.setProductLineId(productLineEsData.getJSONObject("_source").getLong("productLineId"));
//                productLineListItemVO.setProductLineName(productLineEsData.getJSONObject("_source").getString("productLineName"));
//                productLineListItemVO.setStatus(CustomerStatusEnum.valueOf(productLineEsData.getJSONObject("_source").getInteger("status")));
//                productLineListItemVO.setAdvertiserNameList(productLineEsData.getJSONObject("_source").getJSONArray("advertiserNameList").toJavaList(String.class));
//                productLineListItemVO.setSaleList(productLineEsData.getJSONObject("_source").getJSONArray("associatedSales").toJavaList(User.class));
//                productLineListItemVOList.add(productLineListItemVO);
//            }
//            productLineListItemVOPagination.setItems(productLineListItemVOList);
//            return productLineListItemVOPagination;
//        }catch (Exception e){
//            CrmAlertUtils.sendEsAlert("Elasticsearch queryProductLine fail, Exception:"+e.getMessage(), 1,1,1);
//            Log.crm.getLogger().error("[Elasticsearch Exception]",e);
//            throw e;
//        }
//    }
//
//    @Override
//    public Pagination<AdvertiserListItemVO> queryAdvertiser(AdvertiserSearchQuery searchQuery, String username) throws Exception{
//        try {
//            Pagination<AdvertiserListItemVO> advertiserListItemVOPagination = new Pagination<>();
//            advertiserListItemVOPagination.setCurIndex(searchQuery.getCurPageIndex());
//            advertiserListItemVOPagination.setPageSize(searchQuery.getPageSize());
//            JSONObject queryEntity = createQueryEntity(searchQuery, username);
//            if (searchQuery.getCustomerType() != null) {
//                queryEntity.getJSONObject("query").getJSONObject("bool").getJSONArray("must").add(createQueryKv("match", "type", searchQuery.getCustomerType().getId()));
//            }
//            Log.crm.getLogger().info("[Elasticsearch searchQuery]{}",queryEntity.toJSONString());
//            JSONObject respone = JSONObject.parseObject(ElasticsearchUtils.search(index, ADVERTISER_TYPE, queryEntity.toJSONString()));
//            advertiserListItemVOPagination.setRowsCount(respone.getJSONObject("hits").getLong("total"));
//            List<AdvertiserListItemVO> advertiserListItemVOList = new LinkedList<>();
//            for (JSONObject advertiserEsData : respone.getJSONObject("hits").getJSONArray("hits").toJavaList(JSONObject.class)) {
//                AdvertiserListItemVO advertiserListItemVO = new AdvertiserListItemVO();
//                advertiserListItemVO.setAdvertiserId(advertiserEsData.getJSONObject("_source").getLong("advertiserId"));
//                advertiserListItemVO.setAdvertiserName(advertiserEsData.getJSONObject("_source").getString("advertiserName"));
//                advertiserListItemVO.setStatus(CustomerStatusEnum.valueOf(advertiserEsData.getJSONObject("_source").getInteger("status")));
//                advertiserListItemVO.setSaleList(advertiserEsData.getJSONObject("_source").getJSONArray("associatedSales").toJavaList(User.class));
//                advertiserListItemVOList.add(advertiserListItemVO);
//            }
//            advertiserListItemVOPagination.setItems(advertiserListItemVOList);
//            return advertiserListItemVOPagination;
//        }catch (Exception e){
//            CrmAlertUtils.sendEsAlert("Elasticsearch queryAdvertiser fail, Exception:"+e.getMessage(), 1,1,1);
//            Log.crm.getLogger().error("[Elasticsearch Exception]",e);
//            throw e;
//        }
//    }
//
//    @Override
//    public Pagination<AgentListItemVO> queryAgent(AgentSearchQuery searchQuery, String username) throws Exception{
//        try {
//            Pagination<AgentListItemVO> agentListItemVOPagination = new Pagination<>();
//            agentListItemVOPagination.setCurIndex(searchQuery.getCurPageIndex());
//            agentListItemVOPagination.setPageSize(searchQuery.getPageSize());
//            JSONObject queryEntity = createQueryEntity(searchQuery, username);
//            if (searchQuery.getRegionId() != null) {
//                queryEntity.getJSONObject("query").getJSONObject("bool").getJSONArray("must").add(createQueryKv("term", "region.id", searchQuery.getRegionId()));
//            }
//            Log.crm.getLogger().info("[Elasticsearch searchQuery]{}",queryEntity.toJSONString());
//            JSONObject respone = JSONObject.parseObject(ElasticsearchUtils.search(index, AGENT_TYPE, queryEntity.toJSONString()));
//            agentListItemVOPagination.setRowsCount(respone.getJSONObject("hits").getLong("total"));
//            List<AgentListItemVO> agentListItemVOList = new LinkedList<>();
//            for (JSONObject agentEsData : respone.getJSONObject("hits").getJSONArray("hits").toJavaList(JSONObject.class)) {
//                AgentListItemVO agentListItemVO = new AgentListItemVO();
//                agentListItemVO.setAgentId(agentEsData.getJSONObject("_source").getLong("agentId"));
//                agentListItemVO.setAgentName(agentEsData.getJSONObject("_source").getString("agentName"));
//                agentListItemVO.setStatus(CustomerStatusEnum.valueOf(agentEsData.getJSONObject("_source").getInteger("status")));
//                agentListItemVO.setSaleList(agentEsData.getJSONObject("_source").getJSONArray("associatedSales").toJavaList(User.class));
//                agentListItemVOList.add(agentListItemVO);
//            }
//            agentListItemVOPagination.setItems(agentListItemVOList);
//            return agentListItemVOPagination;
//        }catch (Exception e){
//            CrmAlertUtils.sendEsAlert("Elasticsearch queryAgent fail, Exception:"+e.getMessage(), 1,1,1);
//            Log.crm.getLogger().error("[Elasticsearch Exception]",e);
//            throw e;
//        }
//    }
//
//    private JSONObject createQueryKv(String leafClauses, String key, Object val){
//        JSONObject queryKv = new JSONObject();
//        queryKv.put(leafClauses,new JSONObject());
//        queryKv.getJSONObject(leafClauses).put(key,val);
//        return queryKv;
//    }
//
//    private JSONObject createQueryEntity(CrmSearchQuery searchQuery, String username){
//        JSONObject queryEntity = new JSONObject();
//        queryEntity.put("from",(searchQuery.getCurPageIndex()-1)*searchQuery.getPageSize());
//        queryEntity.put("size",searchQuery.getPageSize());
//        JSONObject query = new JSONObject();
//        query.put("bool",new JSONObject());
//        query.getJSONObject("bool").put("must",new JSONArray());
//        query.getJSONObject("bool").put("must_not",new JSONArray());
//        query.getJSONObject("bool").put("should",new JSONArray());
//        query.getJSONObject("bool").put("minimum_number_should_match",1);
//        if(!userService.isEditUser()){
//            if(searchQuery.getCustomerSea() == null) {
//                List<Long> deptSaleIds = userService.listDepartmentUser(username);
//                query.getJSONObject("bool").getJSONArray("should").add(createQueryKv("terms","associatedSales.id",deptSaleIds));
//                query.getJSONObject("bool").getJSONArray("should").add(createQueryKv("terms","rescissionSales.id",deptSaleIds));
//            }else if (searchQuery.getCustomerSea() == CustomerSeaEnum.PRIVATE_SEA) {
//                List<Long> branchIdList = userService.listBranch(username);
//                branchIdList.add(userService.getByName(username).getId());
//                query.getJSONObject("bool").getJSONArray("must").add(createQueryKv("terms","associatedSales.id",branchIdList));
//            }else if(searchQuery.getCustomerSea() == CustomerSeaEnum.PUBLIC_SEA) {
//                List<Long> deptSaleIds = userService.listDepartmentUser(username);
//                query.getJSONObject("bool").getJSONArray("must_not").add(createQueryKv("terms","associatedSales.id",deptSaleIds));
//                query.getJSONObject("bool").getJSONArray("must").add(createQueryKv("terms","rescissionSales.id",deptSaleIds));
//            }
//        }
//        if(searchQuery.getStatus() != null){
//            query.getJSONObject("bool").getJSONArray("must").add(createQueryKv("term","status", searchQuery.getStatus().getId()));
//        }
//        if(StringUtils.isNotEmpty(searchQuery.getKeyword())){
//            JSONObject keyword = createQueryKv("multi_match","query", searchQuery.getKeyword());
//            keyword.getJSONObject("multi_match").put("fields", Arrays.asList("agentName","advertiserName","productLineName","advertiserNameList", "associatedSales.fullname"));
//            query.getJSONObject("bool").getJSONArray("must").add(keyword);
//        }else{
//            queryEntity.put("sort",createQueryKv("last_updated","order","desc"));
//            queryEntity.getJSONObject("sort").getJSONObject("last_updated").put("ignore_unmapped",true);
//        }
//        queryEntity.put("query",query);
//        return queryEntity;
//    }
//
//    @Override
//    public boolean refresh() {
//        Long doneId = 0L;
//        String doneCustomerCategory = "";
//        try {
//            ElasticsearchUtils.sendCmd("DELETE","/"+index,"{}");
//            ElasticsearchUtils.sendCmd("PUT","/"+index,"{}");
//            doneCustomerCategory = CustomerCategoryEnum.AGENT.name();
//            for(Long agentId : agentService.listId()){
//                doneId = agentId;
//                index(agentId,CustomerCategoryEnum.AGENT);
//            }
//            doneCustomerCategory = CustomerCategoryEnum.ADVERTISER.name();
//            for(Long advertiserId : advertiserService.listId()){
//                doneId = advertiserId;
//                index(advertiserId,CustomerCategoryEnum.ADVERTISER);
//            }
//            doneCustomerCategory = CustomerCategoryEnum.PRODUCT_LINE.name();
//            for(Long productLineId : productLineService.listId()){
//                doneId = productLineId;
//                index(productLineId,CustomerCategoryEnum.PRODUCT_LINE);
//            }
//        }catch (Exception e){
//            Log.crm.getLogger().error("[refresh] [" + doneCustomerCategory + "] id: " + doneId ,e);
//            return false;
//        }
//        return true;
//    }

    @Override
    public boolean index(Long id, CustomerCategoryEnum customerCategoryEnum) {
        return false;
    }
}
