package org.study.base.enum_test;

public class EnumTest {
	
	public static void main(String[] args) {
		System.out.println(IsPublish.NO.getKey());
	}
	
	public enum isDelete{
		YES(1),NO(0);
		
		private Integer value;
		
		private isDelete(int value){
			this.value = value;	
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum IsPublish{
		
		YES(1,"已发布"), NO(0,"未发布");
		
		private Long key;

        private String value;

        private IsPublish(Integer key, String value){
            this.key = Long.parseLong(key.toString());
            this.value = value;
        }
        
        public static IsPublish getStatus(Short key){
        	IsPublish[] values = IsPublish.values();
            for (IsPublish object : values){
                if (object.key.equals(key)){
                    return object;
                }
            }
            return null;
        }

        public Long getKey()
        {
            return key;
        }

        public String getValue()
        {
            return value;
        }
	}
}
