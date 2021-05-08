package io.github.anantharajuc.sbat.core_backend.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class SiteSettings 
{
	String siteLogo;
	String siteInitials;
	String siteTitle;
	String siteDescription;
}
