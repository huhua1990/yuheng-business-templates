#!/bin/bash

# 批量迁移所有业务模块脚本

modules=(
  "bpm"
  "crm"
  "pay"
  "erp"
  "hrm"
  "oa"
  "mes"
  "wms"
  "mp"
  "report"
  "pms"
  "fms"
  "im"
  "ai"
)

for module in "${modules[@]}"; do
  echo "========================================="
  echo "Migrating template-$module..."
  echo "========================================="
  
  # 1. 创建目标目录
  mkdir -p "template-$module"
  
  # 2. 复制 Java 代码
  if [ -d "../ruoyi-vue-pro/yudao-module-$module/src/main/java/cn/iocoder/yudao/module/$module" ]; then
    cp -r "../ruoyi-vue-pro/yudao-module-$module/src/main/java/cn/iocoder/yudao/module/$module"/* "template-$module/"
    echo "✓ Copied Java files for $module"
  else
    echo "✗ No Java files found for $module"
  fi
  
  # 3. 替换包名
  find "template-$module" -name "*.java" -exec sed -i '' "s/cn\.iocoder\.yudao\.module\.$module/com.yuheng.template.$module/g" {} + 2>/dev/null
  find "template-$module" -name "*.java" -exec sed -i '' 's/cn\.iocoder\.yudao\.module\.system/com.yuheng.platform.module.system/g' {} + 2>/dev/null
  find "template-$module" -name "*.java" -exec sed -i '' 's/cn\.iocoder\.yudao\.module\.infra/com.yuheng.platform.module.infra/g' {} + 2>/dev/null
  find "template-$module" -name "*.java" -exec sed -i '' 's/cn\.iocoder\.yudao\.framework/com.yuheng.platform.framework/g' {} + 2>/dev/null
  echo "✓ Replaced package names for $module"
  
  # 4. 复制前端代码（如果存在）
  mkdir -p "template-$module/ui"
  if [ -d "../yudao-ui-admin-vue3/src/views/$module" ]; then
    cp -r "../yudao-ui-admin-vue3/src/views/$module" "template-$module/ui/"
    echo "✓ Copied Vue files for $module"
  fi
  
  if [ -d "../yudao-ui-admin-vue3/src/api/$module" ]; then
    mkdir -p "template-$module/ui/api"
    cp -r "../yudao-ui-admin-vue3/src/api/$module" "template-$module/ui/api/"
    echo "✓ Copied API files for $module"
  fi
  
  echo "✓ Migration completed for template-$module"
  echo ""
done

echo "========================================="
echo "All migrations completed!"
echo "========================================="
