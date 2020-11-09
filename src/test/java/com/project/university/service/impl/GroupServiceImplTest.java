package com.project.university.service.impl;

import com.project.university.model.Course;
import com.project.university.model.Group;
import com.project.university.repository.GroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillChangeCourseForGroupAndInvokeSaveForGroup() {
        Course course = spy(Course.class);
        course.setId(anyLong());

        Group group = spy(Group.class);
        group.setId(anyLong());
        group.setCourse(course);

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));

        groupServiceImpl.changeCourseIdGroup(group);

        verify(group).setCourse(course);
        verify(groupRepository).save(group);
    }

    @Test
    void test_WhenServiceWillInvokeItsMethodThenRepoWillChangeNameForGroupAndInvokeSaveForGroup() {
        Group group = spy(Group.class);
        group.setId(anyLong());
        group.setName(anyString());

        when(groupRepository.findById(anyLong())).thenReturn(Optional.of(group));

        groupServiceImpl.changeNameGroup(group);

        verify(group, times(2)).setName(anyString());
        verify(groupRepository).save(group);
     }

     @Test
     void test_WhenServiceWillInvokeItsMethodThenRepoWillInvokeSaveForGroup(){
        Group group = mock(Group.class);
        groupServiceImpl.addNewGroup(group);
        verify(groupRepository).save(group);
    }
}

