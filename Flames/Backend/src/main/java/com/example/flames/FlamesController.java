package com.example.flames;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin
public class FlamesController {

    public char flames(int fcount)
    {
        StringBuilder str = new StringBuilder("FLAMES");
        int j = 0;

        while(str.length() > 1)
        {
            for(int i = 1; i <= fcount; i++, j++)
            {
                if(j == str.length())
                    j = 0;
            }
            j--;
            str.deleteCharAt(j);
        }
        return str.charAt(0);
    }

    @PostMapping("/flames")
    public Map<String,String> getFlames(@RequestBody Map<String,String> data)
    {
        String s1 = data.get("name1").toLowerCase();
        String s2 = data.get("name2").toLowerCase();

        int count = s1.length() + s2.length();

        int[] ch1 = new int[26];
        int[] ch2 = new int[26];

        for(int i=0;i<s1.length();i++)
            ch1[s1.charAt(i)-97]++;

        for(int i=0;i<s2.length();i++)
            ch2[s2.charAt(i)-97]++;

        for(int i=0;i<26;i++)
        {
            while(ch1[i]>=1 && ch2[i]>=1)
            {
                ch1[i]--;
                ch2[i]--;
                count -= 2;
            }
        }

        char c = flames(count);

        String result="";

        switch(c){
            case 'F': result="Friend"; break;
            case 'L': result="Love"; break;
            case 'A': result="Affection"; break;
            case 'M': result="Marriage"; break;
            case 'E': result="Enemy"; break;
            case 'S': result="Sister"; break;
        }

        Map<String,String> map = new HashMap<>();
        map.put("result",result);

        return map;
    }
}
