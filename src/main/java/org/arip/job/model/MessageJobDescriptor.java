package org.arip.job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.arip.job.job.MessageJob;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;

import java.util.*;

import static org.quartz.JobBuilder.*;

/**
 * Created by Arip Hidayat on 1/3/2018.
 */

@Data
public class MessageJobDescriptor {
    @NotBlank
    private String name;
    private String group;
    @NotEmpty
    private String content;
    private Map<String, Object> data = new LinkedHashMap<>();
    @JsonProperty("triggers")
    private List<TriggerDescriptor> triggerDescriptors = new ArrayList<>();

    public MessageJobDescriptor setName(final String name) {
        this.name = name;
        return this;
    }

    public MessageJobDescriptor setGroup(final String group) {
        this.group = group;
        return this;
    }

    public MessageJobDescriptor setContent(String content) {
        this.content = content;
        return this;
    }

    public MessageJobDescriptor setTriggerDescriptors(final List<TriggerDescriptor> triggerDescriptors) {
        this.triggerDescriptors = triggerDescriptors;
        return this;
    }

    /**
     * Method for building Triggers of Job
     */
    @JsonIgnore
    public Set<Trigger> buildTriggers() {
        Set<Trigger> triggers = new LinkedHashSet<>();
        for (TriggerDescriptor triggerDescriptor : triggerDescriptors) {
            triggers.add(triggerDescriptor.buildTrigger());
        }

        return triggers;
    }

    /**
     * Method that builds a JobDetail
     */
    public JobDetail buildJobDetail() {
        JobDataMap jobDataMap = new JobDataMap(getData());
        jobDataMap.put("content", content);
        return newJob(MessageJob.class)
                .withIdentity(getName(), getGroup())
                .usingJobData(jobDataMap)
                .build();
    }

    /**
     * Method that builds a descriptor from JobDetail and Trigger(s)
     */
    public static MessageJobDescriptor buildDescriptor(JobDetail jobDetail, List<? extends Trigger> triggersOfJob) {
        List<TriggerDescriptor> triggerDescriptors = new ArrayList<>();

        for (Trigger trigger : triggersOfJob) {
            triggerDescriptors.add(TriggerDescriptor.buildDescriptor(trigger));
        }

        return new MessageJobDescriptor()
                .setName(jobDetail.getKey().getName())
                .setGroup(jobDetail.getKey().getGroup())
                .setContent(jobDetail.getJobDataMap().getString("content"))
                .setTriggerDescriptors(triggerDescriptors);
    }
}
