/*
 * 책임 작성자: 박혜정
 * 최초 작성일: 2022-11-14
 * 최근 수정일: 2022-11-14
 */

type Developer = {
  name: string;
  github: string;
  blog: string;
};

import { MorakLogo } from '../hyejung/MorakLogo';
import { DeveloperLink } from '../hyejung/DeveloperLink';

const developers: Developer[] = [
  {
    name: '박연우',
    github: 'https://github.com/HyeonWooGa',
    blog: 'https://friedegg556.tistory.com/',
  },
  {
    name: '박혜정',
    github: 'https://github.com/hyejj19',
    blog: 'https://friedegg556.tistory.com/',
  },
  {
    name: '정하승',
    github: 'https://github.com/HA-SEUNG-JEONG',
    blog: 'https://friedegg556.tistory.com/',
  },
  {
    name: '백시온',
    github: 'https://github.com/Shawn9948',
    blog: 'https://friedegg556.tistory.com/',
  },
  {
    name: '양은찬',
    github: 'https://github.com/yangddoddi',
    blog: 'https://friedegg556.tistory.com/',
  },
  {
    name: '정희윤',
    github: 'https://github.com/Tldkt',
    blog: 'https://friedegg556.tistory.com/',
  },
];

export const Footer = () => {
  return (
    <footer className="flex bg-background-gray h-[130px] justify-center w-full border-t absolute bottom-0 items-center mx-auto">
      <div className="flex max-w-[1280px] justify-between w-full">
        <div className="flex w-full flex-col space-y-2">
          <MorakLogo />
          <p>©MORAKL. ALL RIGHTS RESERVED</p>
        </div>
        <div className="flex w-full space-x-3">
          {developers.map((dev, i) => (
            <DeveloperLink github={dev.github} blog={dev.blog} key={i}>
              {dev.name}
            </DeveloperLink>
          ))}
        </div>
      </div>
    </footer>
  );
};
