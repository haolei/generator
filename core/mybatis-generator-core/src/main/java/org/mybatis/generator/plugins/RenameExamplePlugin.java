/**
 * 
 */
package org.mybatis.generator.plugins;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.api.IntrospectedTable;

/**
 * this plugin change all the "Example" prefix or appendix in xml or java file to the 'replaceString'
   the configuration is the same to "org.mybatis.generator.plugins.RenameExampleClassPlugin"
 * @author hhao
 *
 */
public class RenameExamplePlugin extends RenameExampleClassPlugin {
    private String searchString;
    private String replaceString;
    private Pattern pattern;

    /**
     * 
     */
    public RenameExamplePlugin() {
    }

    public boolean validate(List<String> warnings) {

        searchString = properties.getProperty("searchString"); //$NON-NLS-1$
        replaceString = properties.getProperty("replaceString"); //$NON-NLS-1$

        boolean valid = stringHasValue(searchString)
                && stringHasValue(replaceString);

        if (valid) {
            pattern = Pattern.compile(searchString);
        } else {
            if (!stringHasValue(searchString)) {
                warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                        "RenameExampleClassPlugin", //$NON-NLS-1$
                        "searchString")); //$NON-NLS-1$
            }
            if (!stringHasValue(replaceString)) {
                warnings.add(getString("ValidationError.18", //$NON-NLS-1$
                        "RenameExampleClassPlugin", //$NON-NLS-1$
                        "replaceString")); //$NON-NLS-1$
            }
        }

        return valid;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String oldType = introspectedTable.getExampleType();
        Matcher matcher = pattern.matcher(oldType);
        oldType = matcher.replaceAll(replaceString);

        introspectedTable.setExampleType(oldType);
        
        
        introspectedTable.setCountByExampleStatementId("countBy"+replaceString); //$NON-NLS-1$
        introspectedTable.setDeleteByExampleStatementId("deleteBy"+replaceString); //$NON-NLS-1$
        introspectedTable.setSelectByExampleStatementId("selectBy"+replaceString); //$NON-NLS-1$
        introspectedTable.setSelectByExampleWithBLOBsStatementId("selectBy"+replaceString+"WithBLOBs"); //$NON-NLS-1$
        introspectedTable.setUpdateByExampleStatementId("updateBy"+replaceString); //$NON-NLS-1$
        introspectedTable.setUpdateByExampleSelectiveStatementId("updateBy"+replaceString+"Selective"); //$NON-NLS-1$
        introspectedTable.setUpdateByExampleWithBLOBsStatementId("updateBy"+replaceString+"WithBLOBs"); //$NON-NLS-1$
        introspectedTable.setBaseResultMapId("BaseResultMap"); //$NON-NLS-1$
        introspectedTable.setResultMapWithBLOBsId("ResultMapWithBLOBs"); //$NON-NLS-1$
        introspectedTable.setExampleWhereClauseId(replaceString+"_Where_Clause"); //$NON-NLS-1$
        introspectedTable.setMyBatis3UpdateByExampleWhereClauseId("Update_By_"+replaceString+"_Where_Clause"); //$NON-NLS-1$
        
        
    }

}
