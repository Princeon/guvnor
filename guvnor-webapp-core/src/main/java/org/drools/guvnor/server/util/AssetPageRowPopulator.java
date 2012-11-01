/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.guvnor.server.util;

import org.apache.commons.lang.StringUtils;
import org.drools.guvnor.client.rpc.AssetPageRow;
import org.drools.repository.AssetItem;
import org.drools.guvnor.client.rpc.Path;
import org.drools.guvnor.client.rpc.PathImpl;

public class AssetPageRowPopulator {
    public AssetPageRow populateFrom(AssetItem assetItem) {
        AssetPageRow row = new AssetPageRow();
        //REVISIT: get a Path instance from drools-repository-vfs
        Path path = new PathImpl();
        path.setUUID(assetItem.getUUID());
        row.setPath( path );
        row.setFormat( assetItem.getFormat() );
        row.setName( assetItem.getName() );
        row.setDescription( assetItem.getDescription() );
        row.setAbbreviatedDescription( StringUtils.abbreviate( assetItem.getDescription(), 80 ) );
        row.setStateName( assetItem.getStateDescription() );
        row.setCreator( assetItem.getCreator() );
        row.setCreatedDate( assetItem.getCreatedDate().getTime() );
        row.setLastContributor( assetItem.getLastContributor() );
        row.setLastModified( assetItem.getLastModified().getTime() );
        row.setCategorySummary( assetItem.getCategorySummary() );
        row.setExternalSource( assetItem.getExternalSource() );
        row.setDisabled( assetItem.getDisabled() );
        row.setValid(assetItem.getValid());
        return row;
    }
}
