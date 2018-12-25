package com.yichu.lucene.demo.analizer;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.util.Attribute;
import org.apache.lucene.util.AttributeImpl;
import org.apache.lucene.util.AttributeReflector;

/**
 * 自己实现的分词器
 * 实现对英文按空白字符进行分词。
 */
public class MyWhitespaceAnalyzer extends Analyzer {

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer source = new MyWhitespaceTokenizer();                //Tokenizer 分词器
        TokenStream filter = new MyLowerCaseTokenFilter(source);       //TokenStream 分词处理器  过滤
        return new TokenStreamComponents(source, filter);

        //如果要加多个filter
/*      Tokenizer source = new MyWhitespaceTokenizer();
        TokenStream filter = new MyLowerCaseTokenFilter(source);
        TokenStream filter2 = new MyLowerCaseTokenFilter(filter);
        TokenStream filter3 = new MyLowerCaseTokenFilter(filter2);
        return new TokenStreamComponents(source, filter3);*/
    }

    /**
     * 实现对英文按空白字符进行分词。 需要记录的属性信息有: 词
     */
    static class MyWhitespaceTokenizer extends Tokenizer {

        // 需要记录的属性
        // 词
        MyCharAttribute charAttr = this.addAttribute(MyCharAttribute.class);

        // 存词的出现位置   自己添加 Attribute  this.addAttribute

        // 存放词的偏移     自己添加 Attribute

        //
        char[] buffer = new char[255];
        int length = 0;
        int c;

        @Override
        public boolean incrementToken() throws IOException {
            // 清除所有的词项属性
            clearAttributes();
            length = 0;
            while (true) {
                c = this.input.read();

                if (c == -1) {
                    if (length > 0) {
                        // 复制到charAttr
                        this.charAttr.setChars(buffer, length);
                        return true;
                    } else {
                        return false;
                    }
                }

                if (Character.isWhitespace(c)) {
                    if (length > 0) {
                        // 复制到charAttr
                        this.charAttr.setChars(buffer, length);
                        return true;
                    }
                }

                buffer[length++] = (char) c;
            }
        }

    }

    /**
     * TokenFilter: 要进行的处理：转为小写
     */
    public static class MyLowerCaseTokenFilter extends TokenFilter {

        public MyLowerCaseTokenFilter(TokenStream input) {
            super(input);
        }

        MyCharAttribute charAttr = this.addAttribute(MyCharAttribute.class);

        @Override
        public boolean incrementToken() throws IOException {
            boolean res = this.input.incrementToken();
            if (res) {
                char[] chars = charAttr.getChars();
                int length = charAttr.getLength();
                if (length > 0) {
                    for (int i = 0; i < length; i++) {
                        chars[i] = Character.toLowerCase(chars[i]);
                    }
                }
            }
            return res;
        }

    }

    public interface MyCharAttribute extends Attribute {
        void setChars(char[] buffer, int length);

        char[] getChars();

        int getLength();

        String getString();
    }

    public static class MyCharAttributeImpl extends AttributeImpl
            implements MyCharAttribute {
        private char[] chatTerm = new char[255];
        private int length = 0;

        @Override
        public void setChars(char[] buffer, int length) {
            this.length = length;
            if (length > 0) {
                System.arraycopy(buffer, 0, this.chatTerm, 0, length);
            }
        }

        public char[] getChars() {
            return this.chatTerm;
        }

        public int getLength() {
            return this.length;
        }

        @Override
        public String getString() {
            if (this.length > 0) {
                return new String(this.chatTerm, 0, length);
            }
            return null;
        }

        @Override
        public void clear() {
            this.length = 0;
        }

        @Override
        public void reflectWith(AttributeReflector reflector) {

        }

        @Override
        public void copyTo(AttributeImpl target) {

        }
    }

    public static void main(String[] args) {
        String text = "An AttributeSource contains a list of different AttributeImpls, and methods to add and get them. ";
        try {
            Analyzer ana = new MyWhitespaceAnalyzer();
            TokenStream ts = ana.tokenStream("aa", text);  //从 分词器中获取 TokenStream
            MyCharAttribute ca = ts.getAttribute(MyCharAttribute.class);
            ts.reset();
            while (ts.incrementToken()) {    //这里利用了 装饰设计模式    ts指向的是 sink 查看ana.tokenStream中 result代码
                System.out.print(ca.getString() + "|");
            }
            ts.end();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*		try (Analyzer ana = new MyWhitespaceAnalyzer();
                TokenStream ts = ana.tokenStream("aa", text);) {
			MyCharAttribute ca = ts.getAttribute(MyCharAttribute.class);
			ts.reset();
			while (ts.incrementToken()) {
				System.out.print(ca.getString() + "|");
			}
			ts.end();
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}*/


    }

}
