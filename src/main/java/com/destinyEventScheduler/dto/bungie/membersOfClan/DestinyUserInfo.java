package com.destinyEventScheduler.dto.bungie.membersOfClan;
public class DestinyUserInfo
{
    private String membershipId;

    private String membershipType;

    private String iconPath;

    private String displayName;

    public String getMembershipId ()
    {
        return membershipId;
    }

    public void setMembershipId (String membershipId)
    {
        this.membershipId = membershipId;
    }

    public String getMembershipType ()
    {
        return membershipType;
    }

    public void setMembershipType (String membershipType)
    {
        this.membershipType = membershipType;
    }

    public String getIconPath ()
    {
        return iconPath;
    }

    public void setIconPath (String iconPath)
    {
        this.iconPath = iconPath;
    }

    public String getDisplayName ()
    {
        return displayName;
    }

    public void setDisplayName (String displayName)
    {
        this.displayName = displayName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [membershipId = "+membershipId+", membershipType = "+membershipType+", iconPath = "+iconPath+", displayName = "+displayName+"]";
    }
}