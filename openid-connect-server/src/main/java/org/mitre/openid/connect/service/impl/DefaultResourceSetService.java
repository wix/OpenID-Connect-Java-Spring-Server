/*******************************************************************************
 * Copyright 2015 The MITRE Corporation
 *   and the MIT Kerberos and Internet Trust Consortium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package org.mitre.openid.connect.service.impl;

import org.mitre.openid.connect.model.ResourceSet;
import org.mitre.openid.connect.repository.ResourceSetRepository;
import org.mitre.openid.connect.service.ResourceSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jricher
 *
 */
@Service
public class DefaultResourceSetService implements ResourceSetService {

	@Autowired
	private ResourceSetRepository repository;

	@Override
	public ResourceSet saveNew(ResourceSet rs) {
		
		if (rs.getId() != null) {
			throw new IllegalArgumentException("Can't save a new resource set with an ID already set to it.");
		}
		
		ResourceSet saved = repository.save(rs);
		
		return saved;
		
	}

	@Override
	public ResourceSet getById(Long id) {
		return repository.getById(id);
	}

	@Override
	public ResourceSet update(ResourceSet oldRs, ResourceSet newRs) {

		if (oldRs.getId() == null || newRs.getId() == null
				|| oldRs.getId() != newRs.getId()) {
			
			throw new IllegalArgumentException("Resource set IDs mismatched");
			
		}
		
		newRs.setOwner(oldRs.getOwner());
		
		ResourceSet saved = repository.save(newRs);
		
		return saved;
		
	}
	
	
	
}