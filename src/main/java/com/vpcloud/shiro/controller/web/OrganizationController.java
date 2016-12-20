package com.vpcloud.shiro.controller.web;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vpcloud.shiro.pojo.Organization;
import com.vpcloud.shiro.service.OrganizationService;
import com.vpcloud.shiro.service.RoleService;

@Controller
@RequestMapping("/organization")
public class OrganizationController {
	@Resource
	private OrganizationService organservice;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
    private OrganizationService organizationService;
	
	@RequiresPermissions("organization:view")
	@RequestMapping
	public String index(Model model){
		setCommonData(model);
		List<Organization> organ = organservice.findAll();
		model.addAttribute("organList",organ);
		return "/organization/system";
	}
	@RequiresPermissions("organization:create")
    @RequestMapping("/create")
    public String createorgan(Organization organ, RedirectAttributes redirectAttributes) {
		System.out.println(organ.toString());
		organservice.createOrganization(organ);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/organization";
    }
    @RequiresPermissions("organization:delete")
    @RequestMapping("/{id}/delete")
    public String DeleteOrgan(@PathVariable("id") Long id, Model model) {
    	organservice.deleteOrganization(id);
        return "redirect:/organization";
    }
    @RequiresPermissions("organization:update")
    @RequestMapping("/update")
    public String UpdateOrgan(Organization organ, RedirectAttributes redirectAttributes) {
    	System.out.println(organ.toString());
    	organservice.updateOrganization(organ);
        return "redirect:/organization";
    }
	
	private void setCommonData(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        model.addAttribute("roleList", roleService.findAll());
    }
}
