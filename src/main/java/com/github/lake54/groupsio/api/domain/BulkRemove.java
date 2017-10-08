package com.github.lake54.groupsio.api.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class BulkRemove
{

    private String object;
    private Integer removed;
    private Integer totalEmails;
    private List<Error> errors;

    /**
     * No args constructor for use in serialization
     */
    public BulkRemove()
    {
    }

    public BulkRemove(final String object, final Integer removed, final Integer totalEmails, final List<Error> errors)
    {
        super();
        this.object = object;
        this.totalEmails = totalEmails;
        this.errors = errors;
    }

    
    public String getObject()
    {
        return object;
    }
    
    public void setObject(final String object)
    {
        this.object = object;
    }
    
    public BulkRemove withObject(final String object)
    {
        this.object = object;
        return this;
    }
    
    public Integer getTotalEmails()
    {
        return totalEmails;
    }
    
    public void setTotalEmails(final Integer totalEmails)
    {
        this.totalEmails = totalEmails;
    }
    
    public BulkRemove withTotalEmails(final Integer totalEmails)
    {
        this.totalEmails = totalEmails;
        return this;
    }
    
    public List<Error> getErrors()
    {
        return errors;
    }
    
    public void setErrors(final List<Error> errors)
    {
        this.errors = errors;
    }
    
    public BulkRemove withErrors(final List<Error> errors)
    {
        this.errors = errors;
        return this;
    }
    
    public Integer getRemoved()
    {
        return removed;
    }
    
    public void setRemoved(final Integer addedMembers)
    {
        this.removed = removed;
    }
    
    public BulkRemove withRemoved(final Integer removed)
    {
        this.removed = removed;
        return this;
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
    
    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(object).append(totalEmails).append(errors).append(removed).toHashCode();
    }
    
    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if (!(other instanceof BulkRemove))
        {
            return false;
        }
        final BulkRemove rhs = ((BulkRemove) other);
        return new EqualsBuilder().append(object, rhs.object).append(totalEmails, rhs.totalEmails).append(errors, rhs.errors)
                .append(removed, rhs.removed).isEquals();
    }
    
}