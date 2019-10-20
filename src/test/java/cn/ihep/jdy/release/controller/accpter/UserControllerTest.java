package cn.ihep.jdy.release.controller.accpter;

import cn.ihep.jdy.release.Util.JPush.JPushConfig;
import cn.ihep.jdy.release.Util.JSONbuildUtil;
import cn.ihep.jdy.release.dao.pojoRepository.*;
import cn.ihep.jdy.release.pojo.AiCustomer;
import cn.ihep.jdy.release.pojo.AiUser;
import cn.ihep.jdy.release.service.ManagerService.ManagerService;
import cn.ihep.jdy.release.service.cache.CacheService;
import cn.ihep.jdy.release.service.common.CommonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * 此处可以是静态引用，也可以不用静态引用，这样就需要在使用方法的时候加上对应的类
 */



/**
 * UserController Tester.
 *
 * @author <Authors name>
 * @since <pre>Oct 13, 2019</pre>
 * @version 1.0
 */
//测试模板 修改url,name,json语句就行了
//           String url="/manager/changegroups";
//           String name="manager";
//          String user= JSONbuildUtil.toJsonString();
//          RequestBuilder request = post(url)
//          .contentType(MediaType.APPLICATION_JSON_UTF8)
//          .param(name,user);
//          System.out.println("请求：" );
//          System.out.println(request.toString());
//
//          MvcResult mvcResult = mockMvc.perform(request).andDo(print()).andReturn();
@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AiCustomerRepository aiCustomerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerFollowRepository customerFollowRepository;

    @Autowired
    private CustomerVisitRepository customerVisitRepository;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AiUserRepository aiUserRepository;

    @Autowired
    private JPushConfig jPushConfig;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private RobotRecordRepository robotRecordRepository;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: login1(String mobileAndpass)
     *
     */
    @Test
    public void testLogin1() throws Exception {
//TODO: Test goes here...
//    发送get请求：
//    RequestBuilder request = get(controller接口方法对应url);
//    mvc.perform(request)
        //MockMvcResultHandlers.print()
//
        String JSONStr=" {\"mobile\": \"15804246695\",\"pass\": \"zh15804246695\"}";
        String url="/user/login1";
        String name="mobileAndpass";
        mvcTest(url,JSONStr,name);


    }

    /**
     *
     * Method: login(@RequestParam String mobile, @RequestParam String pass)
     *
     */
    @Test
    public void testLogin() throws Exception {
//TODO: Test goes here...
        List<AiCustomer> all = aiCustomerRepository.findAll();
        all.forEach(c->{
            Long aiUserId=c.getCounselorId();
            if(aiUserId>0){
                AiUser aiUser = aiUserRepository.findByUserId(aiUserId);
                if(aiUser!=null){

                    Long companyId = aiUser.getCompanyId();
                    c.setCompanyId(companyId);
                    aiCustomerRepository.save(c);
                }
            }

        });
    }

    /**
     *
     * Method: workStatus(@RequestParam int status, Principal principal)
     *记录 0
     * num=5
     * groupId=0
     * 记录 1
     * num=4
     * groupId=1
     * 记录 2
     * num=3
     * groupId=2
     * 记录 3
     * groupId=3
     * num=2
     */
    @Test
    public void testCountGroup() throws Exception {
//TODO: Test goes here...
        List<Map<String, Object>> list = aiUserRepository.countByCompanyIdGrAndGroupId(25L);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        list.forEach(c->{
            System.out.println("记录 "+ list.indexOf(c));

           c.forEach((k,v)->{
               System.out.println(k+"="+v );
           });
        });
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    /**
     *
     * Method: getWorkStatus(Principal principal)
     *
     */
    @Test
    public void testGetGroupBycompanyId() throws Exception {
//TODO: Test goes here...
        String url="/manager/getGroupbyid";
        String JSONStr= "{\"userId\":7,\"groupId\":7}";
        String name="manager";
        mvcTest(url,JSONStr,name);

    }

    /**
     *
     * Method: get(Principal principal)
     *
     */
    @Test
    public void testGet() throws Exception {
//TODO: Test goes here...
        String mobileAndpass=" {\"mobile\": \"15804246695\",\"pass\": \"zh15804246695\"}";
        String accpter="{\"userId\":9}";
        //String accpter="{}";
        String url="/user/get1";
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1NzMiLCJzY29wZSI6IlJPTEVfU0FMRVIsIiwiZXhwIjoxNTcxMjI5NjYyLCJpYXQiOjE1NzExNDMyNjJ9.ev_HEdytt8ptGdOjtUS7FikOpCNrIp1H0DV95pbW36i8IWb1r3t2IO_8kJQr7U0Bi5KAr0mmTVIrc5Zytbq1qA";
        String name="accpter";
        RequestBuilder request = post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param(name,accpter)
                .param("token",token);
        //  .param("mobileAndpass", mobileAndpass) ;
        System.out.println("请求：" );
        System.out.println(request.toString());

        MvcResult mvcResult = mockMvc.perform(request).andDo(print()).andReturn();

    }

    /**
     *
     * Method: setPassCount(@RequestParam String ids, @RequestParam String passCounts)
     *
     */
    @Test
    public void testSetPassCount() throws Exception {
//TODO: Test goes here... 
    }

    /**
     *
     * Method: textSend(@RequestParam String msg)
     *
     */
    @Test
    public void testTextSend() throws Exception {
//TODO: Test goes here...



    }

    /**
     *
     * Method: addGroup(Long userId)
     *
     */
    @Autowired
    private ManagerService groupService;



    /**
     *
     * Method: delGroup(Long UserId, int GroupId)
     *
     */
    @Test
    public void testDelGroup() throws Exception {
//TODO: Test goes here...
        String url="/manager/delgroup";
        String JSONStr="{\"userId\":6,\"GroupId\":3}";
        String name="manager";
        mvcTest(url,JSONStr,name);
    }

    /**
     *
     * Method: changegroup(Long userId, int GroupId)
     *
     */
    @Test
    public void testChangegroup() throws Exception {
//TODO: Test goes here...
        String url="/manager/changegroup";
        String JSONStr="{\"userId\":7,\"AiuserId\":604,\"GroupId\":5}";
        String name="manager";
        mvcTest(url,JSONStr,name);
    }

    @Test
    public void testChangegroups() throws Exception {
//TODO: Test goes here...
        //{users=[{"userIds":[7,8,9],"GroupId":3}]}
        String url="/manager/changegroups";
        String JSONStr= JSONbuildUtil.toJsonString();
        String name="manager";
        mvcTest(url,JSONStr,name);
    }
    @Test
    public void testsetIndex()throws Exception{
        String url="/manager/setIndex";
        String JSONStr=JSONbuildUtil.toJsonString();
        String name="manager";
        mvcTest(url,JSONStr,name);

    }
    @Test
    public void testsettimes()throws Exception{
        String url="/manager/setTimes";
        String JSONStr=JSONbuildUtil.toJsonString();
        String name="manager";
        mvcTest(url,JSONStr,name);

    }
    @Test
    public void testsetindexlist()throws Exception{
        String url="/manager/setIndexlist";
        String JSONStr=JSONbuildUtil.toJsonString();
        String name="manager";
        mvcTest(url,JSONStr,name);

    }
    @Test
    public void testfirstVisit()throws Exception{
        String url="/visit/first";
        String JSONStr=JSONbuildUtil.toJsonString();
        String name="customer";
        mvcTest(url,JSONStr,name);


    }
    public void mvcTest(String url,String user, String name)throws Exception{

        RequestBuilder request = post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param(name,user);
        System.out.println("请求：" );
        System.out.println(request.toString());

        MvcResult mvcResult = mockMvc.perform(request).andDo(print()).andReturn();

    }
}

