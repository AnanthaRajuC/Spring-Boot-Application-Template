package test.integration;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.anantharajuc.sbtest.SBtemplateApplication;
import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.Plan;
import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.Role;
import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.User;
import io.github.anantharajuc.sbtest.backend.persistence.domain.backend.UserRole;
import io.github.anantharajuc.sbtest.backend.persistence.repositories.PlanRepository;
import io.github.anantharajuc.sbtest.backend.persistence.repositories.RoleRepository;
import io.github.anantharajuc.sbtest.backend.persistence.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SBtemplateApplication.class)
public class RepositoriesIntegrationTest 
{
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Long BASIC_PLAN_ID = 1L;
	private static final Long BASIC_ROLE_ID = 1L;
	
	public void init()
	{
		Assert.assertNotNull(planRepository);
		Assert.assertNotNull(roleRepository);
		Assert.assertNotNull(userRepository);
	}
	
	@Test
	public void testCreateNewPlan() throws Exception
	{
		Plan basicPlan = createBasicPlan(); 
		planRepository.save(basicPlan);
		Optional<Plan> retrievedPlan = planRepository.findById(BASIC_PLAN_ID);
		Assert.assertNotNull(retrievedPlan); 
	}
	
	@Test
	public void testCreateNewRole() throws Exception
	{
		Role userRole = createBasicRole(); 
		roleRepository.save(userRole);
		Optional<Role> retrievedRole = roleRepository.findById(BASIC_ROLE_ID);
		Assert.assertNotNull(retrievedRole); 
	}
	
	private Plan createBasicPlan() 
	{
		Plan plan = new Plan();
		plan.setId(BASIC_PLAN_ID);
		plan.setName("Basic");
		return plan;
	}
	
	private Role createBasicRole()
	{
		Role role = new Role();
		role.setId(BASIC_ROLE_ID);
		role.setName("ROLE_USER");
		return role;
	}
}
