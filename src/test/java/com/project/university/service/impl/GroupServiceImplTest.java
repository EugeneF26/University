package com.project.university.service.impl;

import com.project.university.model.Group;
import com.project.university.repository.GroupRepository;
import com.project.university.repository.exception.DaoLayerException;
import com.project.university.repository.exception.DataNotFoundException;
import com.project.university.service.impl.GroupServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class GroupServiceImplTest {

    @InjectMocks
    private GroupServiceImpl groupServiceImpl;

    @Mock
    private GroupRepository groupRepository;

    @Test
    void test_WhenTheMethodReturnDataIsNotNull() {
       assertThat(groupServiceImpl.getGroups(), hasItems());
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillInvokeItsMethodForDeletedGroup() {
        groupServiceImpl.deleteGroup(anyLong());
       verify(groupRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillInvokeItsMethodForGetGroup() {
        groupServiceImpl.getGroup(anyLong());
        verify(groupRepository, times(1)).getOne(anyLong());
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillInvokeItsMethodForSaveGroup() {
        Group group = spy(Group.class);
        groupServiceImpl.addNewGroup(group);
        verify(groupRepository, times(1)).save(any(Group.class));
    }
}