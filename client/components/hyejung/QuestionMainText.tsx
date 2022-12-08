/*
 * 책임 작성자: 박혜정
 * 최초 작성일: 2022-11-29
 * 최근 수정일: 2022-11-29
 */
import Dompurify from 'dompurify';

type MainTextProps = {
  children: string;
};

// 본문 텍스트
export const QuestionMainText = ({ children }: MainTextProps) => {
  return (
    <main className="flex">
      <div
        className="prose max-w-full"
        dangerouslySetInnerHTML={{ __html: Dompurify.sanitize(children) }}
      />
    </main>
  );
};
