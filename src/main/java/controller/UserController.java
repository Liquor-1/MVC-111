package controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/fileupload3")
    public String fileupload3(MultipartFile upload) throws Exception {
        String path = "http://localhost:9090/uploads/";
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filename = upload.getOriginalFilename();
        filename = uuid + "_" + filename;
        Client client = Client.create();
        WebResource webResource = client.resource(path + filename);
        webResource.put(upload.getBytes());

        return "success";
    }

    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("Spring文件上传");
        //使用组件fileupload完成上传
        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);
        if(!file.exists()){
            //创建文件夹
            file.mkdirs();
        }
                //获取上传名称
                String filename = upload.getOriginalFilename();
                //设置文件名唯一值 uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                //完成文件上传
                upload.transferTo(new File(path,filename));
                return "success";
    }

    @RequestMapping("/fileupload1")
    public String fileupload1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        //使用组件fileupload完成上传
        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //判断路径是否存在
        File file = new File(path);
        if(!file.exists()){
            //创建文件夹
            file.mkdirs();
        }
        //
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            //判断，是否是上传文件项
            if(item.isFormField()){
                //说明普通表单项
            }else {
                //上传文件项
                //获取上传名称
                String filename = item.getName();
                //设置文件名唯一值 uuid
                String uuid = UUID.randomUUID().toString().replace("-", "");
                filename = uuid + "_" + filename;
                //完成文件上传
                item.write(new File(path,filename));
                //删除临时文件
                item.delete();

            }
        }
        return "success";
    }
}
